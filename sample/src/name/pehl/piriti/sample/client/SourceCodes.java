package name.pehl.piriti.sample.client;

/**
 * @author $Author: harald.pehl $
 * @version $Date: 2010-05-18 23:30:44 +0200 (Di, 18 Mai 2010) $ $Revision: 299
 *          $
 */
public interface SourceCodes
{
    String JSON_TO_POJO = "ClientResource clientResource = new ClientResource(\"/rest/v1/books\");\n"
            + "clientResource.setOnResponse(new Uniform() {\n" + "    @Override\n"
            + "    public void handle(Request request, Response response) {\n"
            + "        // Book.JSON is a JsonReader\n" + "        PiritiJsonRepresentation<Book> representation = \n"
            + "            new PiritiJsonRepresentation<Book>(Book.JSON, response.getEntity());\n" + "        try {\n"
            + "            List<Book> books = representation.getModels();\n" + "        }\n"
            + "        catch (IOException e) {}\n" + "    }\n" + "});\n"
            + "clientResource.get(MediaType.APPLICATION_JSON);";

    String JSON_TO_GXT_MODEL = "ClientResource clientResource = new ClientResource(\"/rest/v1/books\");\n"
            + "clientResource.setOnResponse(new Uniform() {\n" + "    @Override\n"
            + "    public void handle(Request request, Response response) {\n"
            + "        // BookModel.JSON is a JsonModelReader\n"
            + "        PiritiJsonRepresentation<BookModel> representation = \n"
            + "            new PiritiJsonRepresentation<BookModel>(BookModel.JSON, response.getEntity());\n"
            + "        try {\n" + "            List<BookModel> books = representation.getModels();\n" + "        }\n"
            + "        catch (IOException e) {}\n" + "    }\n" + "});\n"
            + "clientResource.get(MediaType.APPLICATION_JSON);";

    String XML_TO_POJO = "ClientResource clientResource = new ClientResource(\"/rest/v1/books\");\n"
            + "clientResource.setOnResponse(new Uniform() {\n" + "    @Override\n"
            + "    public void handle(Request request, Response response) {\n" + "        // Book.XML is a XmlReader\n"
            + "        PiritiXmlRepresentation<Book> representation = \n"
            + "            new PiritiXmlRepresentation<Book>(Book.XML, response.getEntity());\n" + "        try {\n"
            + "            List<Book> books = representation.getModels();\n" + "        }\n"
            + "        catch (IOException e) {}\n" + "    }\n" + "});\n" + "clientResource.get(MediaType.TEXT_XML);";

    String XML_TO_GXT_MODEL = "ClientResource clientResource = new ClientResource(\"/rest/v1/books\");\n"
            + "clientResource.setOnResponse(new Uniform() {\n" + "    @Override\n"
            + "    public void handle(Request request, Response response) {\n"
            + "        // BookModel.XML is a XmlReader\n"
            + "        PiritiXmlRepresentation<BookModel> representation = \n"
            + "            new PiritiXmlRepresentation<BookModel>(BookModel.XML, response.getEntity());\n"
            + "        try {\n" + "            List<BookModel> books = representation.getModels();\n" + "        }\n"
            + "        catch (IOException e) {}\n" + "    }\n" + "});\n" + "clientResource.get(MediaType.TEXT_XML);";
}
