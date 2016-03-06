import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.xml.XmlCollectionRenderer
import seng5199.twtr.Account

// Place your Spring DSL code here
beans = {
    // The name of the component is not relevant.
    // The constructor argument Account sets the componentType for
    // the collection renderer.
    jsonBookCollectionRenderer(JsonCollectionRenderer, Account)
    xmlBookCollectionRenderer(XmlCollectionRenderer, Account)
}
