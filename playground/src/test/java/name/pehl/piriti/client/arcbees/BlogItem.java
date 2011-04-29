package name.pehl.piriti.client.arcbees;

import java.util.Date;
import java.util.List;

import name.pehl.piriti.commons.client.Format;
import name.pehl.piriti.commons.client.Path;
import name.pehl.piriti.xml.client.XmlReader;

import com.google.gwt.core.client.GWT;

/**
 * @author $Author$
 * @version $Date$ $Revision: 1478
 *          $
 */
//@formatter:off
public class BlogItem
{
    public interface BlogItemReader extends XmlReader<BlogItem> {}
    public static final BlogItemReader XML = GWT.create(BlogItemReader.class);

    private String title;
    @Format("EEE, dd MMM yyyy HH:mm:ss Z") private Date pubDate;
    @Path("dc:creator") private String creator;
    private String link;
    String description;
    @Path("category") private List<String> categories;


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
