package name.pehl.piriti.client.campaign;

import java.util.Date;

import name.pehl.piriti.json.client.JsonReader;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;

// @formatter:off
public class Campaign
{
    public static interface CampaignReader extends JsonReader<Campaign> { }
    public static final CampaignReader READER = GWT.create(CampaignReader.class);

    private static int nextID = 1001;
    private static DateTimeFormat dateFormat = DateTimeFormat.getFormat("MM-dd-yyyy");

    public String campaignId;
    public String clientName;
    public String campaignName;
    public String productName;
    public String mediaCode;
    public String clientCode;
    public String productCode;
    public String estimateCode;

    private long buyScale;
    private long buyTotal;
    private long buyBudget;
    private boolean isBuyBudgetApproved;
    private String buyTotalDiscrepencyPercent;
    private int totalMonths;
    private int reconciledMonths;

    private Date flightStart;
    private Date flightEnd;

    private int needsAttentionProposals;
    private int needsAttentionIos;
    private int needsAttentionAdServers;
    private int needsAttentionFinancials;


    public Campaign()
    {

        campaignName = new String("");
        flightStart = new Date();
        flightEnd = new Date();
        buyBudget = 0;
    }


    public String generateCampaignID()
    {
        // This is pretty pretty pretty dumb
        nextID++;
        return new Integer(nextID).toString();
    }


    public void setCampaignID(String campaignID)
    {
        this.campaignId = campaignID;
    }


    public String getCampaignID()
    {
        return campaignId;
    }


    public void setClientName(String clientName)
    {
        this.clientName = clientName;
    }


    public String getClientName()
    {
        return clientName;
    }


    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }


    public String getCampaignName()
    {
        return campaignName;
    }


    public void setFlightStartDateFromString(String string)
    {
        this.flightStart = dateFormat.parse(string);
    }


    public String getFlightStartDateString()
    {
        return dateFormat.format(flightStart);
    }


    public void setFlightEndDateFromString(String string)
    {
        this.flightEnd = dateFormat.parse(string);
    }


    public String getFlightEndDateString()
    {
        return dateFormat.format(flightEnd);
    }


    public void setFlightStartDate(Date flightStartDate)
    {
        this.flightStart = flightStartDate;
    }


    public Date getFlightStartDate()
    {
        return flightStart;
    }


    public void setFlightEndDate(Date flightEndDate)
    {
        this.flightEnd = flightEndDate;
    }


    public Date getFlightEndDate()
    {
        return flightEnd;
    }


    public void setBuyBudget(long buyBudget)
    {
        this.buyBudget = buyBudget;
    }


    public long getBuyBudget()
    {
        return buyBudget;
    }


    /*******************************************/

    public void setProductName(String productName)
    {
        this.productName = productName;
    }


    public String getProductName()
    {
        return this.productName;
    }


    public void setMediaCode(String mediaCode)
    {
        this.mediaCode = mediaCode;
    }


    public String getMediaCode()
    {
        return this.mediaCode;
    }


    public void setClientCode(String clientCode)
    {
        this.clientCode = clientCode;
    }


    public String getClientCode()
    {
        return this.clientCode;
    }


    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }


    public String getProductCode()
    {
        return this.productCode;
    }


    public void setEstimateCode(String estimateCode)
    {
        this.estimateCode = estimateCode;
    }


    public String getEstimateCode()
    {
        return this.estimateCode;
    }


    public void setBuyScale(long buyScale)
    {
        this.buyScale = buyScale;
    }


    public long getBuyScale()
    {
        return this.buyScale;
    }


    public void setBuyTotal(long buyTotal)
    {
        this.buyTotal = buyTotal;
    }


    public long getBuyTotal()
    {
        return this.buyTotal;
    }


    public void setIsBuyBudgetApproved(boolean isBuyBudgetApproved)
    {
        this.isBuyBudgetApproved = isBuyBudgetApproved;
    }


    public boolean IsBuyBudgetApproved()
    {
        return this.isBuyBudgetApproved;
    }


    public void setBuyTotalDiscrepencyPercent(String buyTotalDiscrepencyPercent)
    {
        this.buyTotalDiscrepencyPercent = buyTotalDiscrepencyPercent;
    }


    public String getBuyTotalDiscrepencyPercent()
    {
        return this.buyTotalDiscrepencyPercent;
    }


    public void setTotalMonths(int totalMonths)
    {
        this.totalMonths = totalMonths;
    }


    public int getTotalMonths()
    {
        return this.totalMonths;
    }


    public void setReconciledMonths(int reconciledMonths)
    {
        this.reconciledMonths = reconciledMonths;
    }


    public int getReconciledMonths()
    {
        return this.reconciledMonths;
    }


    public void setNeedsAttentionProposals(int needsAttentionProposals)
    {
        this.needsAttentionProposals = needsAttentionProposals;
    }


    public int getNeedsAttentionProposals()
    {
        return this.needsAttentionProposals;
    }


    public void setNeedsAttentionIos(int needsAttentionIos)
    {
        this.needsAttentionIos = needsAttentionIos;
    }


    public int getNeedsAttentionIos()
    {
        return this.needsAttentionIos;
    }


    public void setNeedsAttentionAdServers(int needsAttentionAdServers)
    {
        this.needsAttentionAdServers = needsAttentionAdServers;
    }


    public int getNeedsAttentionAdServers()
    {
        return this.needsAttentionAdServers;
    }


    public void setNeedsAttentionFinancials(int needsAttentionFinancials)
    {
        this.needsAttentionFinancials = needsAttentionFinancials;
    }


    public int getNeedsAttentionFinancials()
    {
        return this.needsAttentionFinancials;
    }
}
