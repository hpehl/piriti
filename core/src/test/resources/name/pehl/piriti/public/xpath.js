var xmlIn = null;
var xmlOut = null;
function initEditors()
{
    xmlIn = CodeMirror.fromTextArea('xmlIn',
    {
        height : "350px",
        parserfile : "parsexml.js",
        stylesheet : "codemirror/xmlcolors.css",
        path : "codemirror/",
        lineNumbers : true
    });

    xmlOut = CodeMirror.fromTextArea('xmlOut',
    {
        height : "150px",
        parserfile : "parsexml.js",
        stylesheet : "codemirror/xmlcolors.css",
        path : "codemirror/",
        readOnly : true
    });
}


function select()
{
    var result = "";

    var xmlInValue = xmlIn.getCode();
    var xpath = document.getElementById("xpath").value;
    var namespace = document.getElementById("namespace").value;
    if (xmlInValue == null || xmlInValue == "" || xpath == null || xpath == "" || namespace == null || namespace == "")
    {
        result = "No xml, xpath and/or namespace given";
    } 
    else
    {
        var xmlDoc = Sarissa.getDomDocument();
        xmlDoc = (new DOMParser()).parseFromString(xmlInValue, "text/xml");
        Sarissa.setXpathNamespaces(xmlDoc, namespace);
        var rootElement = xmlDoc.documentElement;
        try
        {
            var nodeList = rootElement.selectNodes(xpath);
            if (nodeList == null || nodeList.length == 0)
            {
                result = "No result";
            } 
            else
            {
                for (i = 0; i < nodeList.length; i++)
                {
                    result += new XMLSerializer().serializeToString(nodeList[i]) + "\n";
                }
            }
        } 
        catch (e)
        {
            result = "Error processing xpath: " + e;
        }
    }
    xmlOut.setCode(result);
}