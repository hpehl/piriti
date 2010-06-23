var namespaces = 'xmlns="http://code.google.com/p/piriti" xmlns:foo="http://code.google.com/p/piriti/foo" xmlns:bar="http://code.google.com/p/piriti/bar"';
var namespaces2 = 'xmlns:default="http://code.google.com/p/piriti" xmlns:foo="http://code.google.com/p/piriti/foo" xmlns:bar="http://code.google.com/p/piriti/bar"';
var xmlString = '<?xml version="1.0" encoding="UTF-8"?><lotteryTicket ' + namespaces + ' foo:date="10.10.2010">'
        + '<foo:player vip="true" foo:gender="male" bar:age="42">'
        + '<foo:firstname>Homer</foo:firstname><foo:surname>Simpson</foo:surname>'
        + '<bar:address>24, evergreen terrace, springfield</bar:address></foo:player>'
        + '<numbers game="6x49"><number>4</number><number>8</number><number>15</number><number>16</number>'
        + '<number>23</number><number>42</number></numbers></lotteryTicket>';

var xmlDoc = Sarissa.getDomDocument();
xmlDoc = (new DOMParser()).parseFromString(xmlString, "text/xml");
Sarissa.setXpathNamespaces(xmlDoc, namespaces2);

function select(xpath)
{
    if (xpath == null || xpath == "")
    {
        return;
    }

    var result = "";
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
                result = result.replace(/</g, "&lt;");
                result = result.replace(/>/g, "&gt;");
            }
        }
    }
    catch (e)
    {
        result = "Error processing xpath: " + e;
    }
    document.getElementById("result").innerHTML = result; 
}