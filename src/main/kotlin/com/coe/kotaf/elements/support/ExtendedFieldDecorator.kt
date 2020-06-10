package com.coe.kotaf.elements.support

import com.coe.kotaf.elements.Component
import com.coe.kotaf.elements.Element
import com.coe.kotaf.elements.factory.createComponent
import org.openqa.selenium.SearchContext
import org.openqa.selenium.WebElement
import org.openqa.selenium.WrapsElement
import org.openqa.selenium.interactions.Locatable
import org.openqa.selenium.support.FindAll
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.FindBys
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory
import org.openqa.selenium.support.pagefactory.ElementLocator
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory
import org.openqa.selenium.support.pagefactory.FieldDecorator
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Proxy
import kotlin.reflect.KClass

class ExtendedFieldDecorator(
    searchContext: SearchContext
) : FieldDecorator {
    /**
    Specify custom LOCATOR FACTORY here (if any)
     */
    private val factory: ElementLocatorFactory = DefaultElementLocatorFactory(searchContext)

    /**
     * This method is called by PageFactory on all fields to decide how to decorate the field.
     *
     * @param loader The class loader that was used for the page object
     * @param field The field that may be decorated.
     * @return Value to decorate the field with or null if it shouldn't be decorated. If non-null,
     * must be assignable to the field.
     */
    override fun decorate(loader: ClassLoader, field: Field): Any? {
        return if (isDecorateField(field)) {
            val locator: ElementLocator = factory.createLocator(field)
            when {
                field.type canBeAssignedTo Component::class -> proxyForComponent(field.type, loader, locator)
                field.type canBeAssignedTo List::class -> proxyForListLocator(getListType(field), loader, locator)
                else -> proxyForLocator(field.type, loader, locator)
            }
        } else null
    }

    private fun isDecorateField(field: Field): Boolean {
        val isValidType = with(field.type) {
            when {
                this canBeAssignedTo List::class -> isDecorateList(field)
                else -> isDecorateType(this)
            }
        }
        return isFindByAnnotationPresent(field) && isValidType
    }

    private fun isFindByAnnotationPresent(field: Field): Boolean =
        with(field) {
            isAnnotationPresent(FindBy::class.java) || isAnnotationPresent(FindBys::class.java) ||
                    isAnnotationPresent(FindAll::class.java)
        }

    private fun isDecorateList(field: Field): Boolean =
        field.genericType is ParameterizedType && isDecorateType(getListType(field))

    private fun isDecorateType(clazz: Class<*>): Boolean =
        with(clazz) {
            canBeAssignedTo(Element::class) || canBeAssignedTo(WebElement::class)
        }

    private fun getListType(field: Field): Class<*> =
        with(field.genericType as ParameterizedType) {
            actualTypeArguments[0].run {
                Class.forName(typeName.split(" ").last())
            }
        }
}

infix fun Class<out Any>.canBeAssignedTo(clazz: KClass<*>): Boolean =
    clazz.javaObjectType.isAssignableFrom(this)