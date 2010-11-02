package name.pehl.piriti.client.gwttest.arcbees;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.client.xml.Xml;
import name.pehl.piriti.client.xml.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author:$
 * @version $Date:$ $Revision:$
 */
//@formatter:off
public class BlogItem
{
    public interface BlogItemReader extends XmlReader<BlogItem> {}
    public static final BlogItemReader XML = GWT.create(BlogItemReader.class);

    @Xml private String title;
    @Xml(format = "EEE, dd MMM yyyy HH:mm:ss Z") private Date pubDate;
    @Xml("dc:creator") private String creator;
    @Xml("link") private String link;
    @Xml private String description;
    @Xml("category") private List<String> categories;


    public String getTitle()
    {
        return title;
    }


    public void setTitle(String title)
    {
        this.title = title;
    }


    public Date getPubDate()
    {
        return pubDate;
    }


    public void setPubDate(Date pubDate)
    {
        this.pubDate = pubDate;
    }


    public String getCreator()
    {
        return creator;
    }


    public void setCreator(String creator)
    {
        this.creator = creator;
    }


    public String getLink()
    {
        return link;
    }


    public void setLink(String link)
    {
        this.link = link;
    }


    public String getDescription()
    {
        return description;
    }


    public void setDescription(String description)
    {
        this.description = description;
    }


    public List<String> getCategories()
    {
        return categories;
    }


    public void setCategories(List<String> categories)
    {
        this.categories = categories;
    }
}
