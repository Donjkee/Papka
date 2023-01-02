import org.asynchttpclient.util.Assertions;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table
{
    private WebElement table;
    private WebElement scrollBar;
    private WebElement scrollButton;

    private int factHeight = 0;
    private int rowCount = 0;
    private int colCount = 0;
    private int scrollHeight = 0;

    public Table(@NotNull WebElement table)
    {
        this.table = table;
        rowCount = Integer.parseInt(table.getAttribute("aria-rowcount"));
        colCount = Integer.parseInt(table.getAttribute("aria-colcount"));
    }

    public WebElement[] getFirstRow()
    {
        WebElement[] elements = new WebElement[colCount];

        for(int i = 1; i < colCount; i++)
        {
            elements[i] = table.findElement(By.xpath("((//tr/th)[" + (i + 1) + "]/div/div)[1]"));
        }

        return elements;
    }

    /*
    public WebElement getRow(int i)
    {
        return table.findElement(By.xpath("//tbody//tr[@aria-rowindex='" + i + "']"));
    }

    public int checkTable()
    {
        WebElement row = getRow(i);
        Map<String, String> map = new HashMap<>();
        map.put(firstRowElements[j].getAttribute("textContent"),
            row.findElement(By.xpath("(//td)[j + 1]")).getAttribute("textContent"));
    }
     */

    public int checkTable()
    {
        WebElement[] firstRowElements = getFirstRow();
        Map<String, String> map = new HashMap<>();
        for(int i = 1; i <= rowCount; i++)
        {
            for(int j = 1; j < colCount; j++)
            {
                map.put(firstRowElements[j].getAttribute("textContent"), table.findElement(By.xpath(
                        "(//tbody//tr[@aria-rowindex='" + i + "']//td)[" + (j + 1)
                        + "]")).getAttribute("textContent"));
            }

            map.remove("Country");
            if(!checkRow(map, i))
            {
                return i;
            }
            map.clear();
            scroll(scrollHeight);
        }

        return 0;
    }

    public boolean checkRow(Map<String, String> map, int row)
    {
        WebElement first;
        List<WebElement> second;
        for (String key : map.keySet())
        {
            String temp = map.get(key).replace("$", "");
            if(temp.contains("%"))
            {
                temp = "%";
            }
            first = table.findElement(By.xpath("//thead//*[text()='" + key + "']/../.."));
            second = table.findElements(By.xpath("//tr[@aria-rowindex='" + row + "']//*[text()=\"" + temp
                    + "\"]//ancestor::td"));
            String o = first.getAttribute("ariaColIndex");
            String s;
            for (WebElement element : second)
            {
                s = element.getAttribute("ariaColIndex");
                if (o.equals(s))
                {
                    return true;
                }
            }
        }
        return false;
    }

    void scroll(int px)
    {
        int i = Integer.parseInt(scrollBar.getAttribute("aria-valuenow"));
        int scrollPerClick = Integer.parseInt(scrollBar.getAttribute("thumbSize"));
        int end = factHeight + px;
        for(; i < end - scrollPerClick; i += scrollPerClick)
        {
            scrollButton.click();
        }
        factHeight += px;
    }
    
    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColCount() {
        return colCount;
    }

    public void setColCount(int colCount) {
        this.colCount = colCount;
    }

    public WebElement getTable()
    {
        return table;
    }

    public void setTable(WebElement table)
    {
        this.table = table;
    }

    public WebElement getScrollBar()
    {
        return scrollBar;
    }

    public void setScrollBar(WebElement scrollBar)
    {
        this.scrollBar = scrollBar;
    }

    public WebElement getScrollButton()
    {
        return scrollButton;
    }

    public void setScrollButton(WebElement scrollButton)
    {
        this.scrollButton = scrollButton;
    }

    public int getScrollHeight() {
        return scrollHeight;
    }

    public void setScrollHeight(int scrollHeight) {
        this.scrollHeight = scrollHeight;
    }
}
