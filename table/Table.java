import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table
{
    private WebElement table;
    private WebElement scrollBar;
    private WebElement scrollDownButton;
    private WebElement scrollUpButton;

    private int factHeight = 0;
    private int rowCount = 0;
    private int colCount = 0;
    private int scrollHeight = 0;
    private Actions actions;

    public Table(@NotNull WebElement table)
    {
        this.table = table;
        rowCount = Integer.parseInt(table.getAttribute("aria-rowcount"));
        colCount = Integer.parseInt(table.getAttribute("aria-colcount"));
    }

    void scrollDown(int px)
    {
        int i = Integer.parseInt(scrollBar.getAttribute("aria-valuenow"));
        int scrollPerClick = Integer.parseInt(scrollBar.getAttribute("thumbSize"));
        int end = factHeight + px;
        for(; i < end - scrollPerClick; i += scrollPerClick)
        {
            scrollDownButton.click();
        }
        factHeight += px;
    }

    void ScrollUp(int px)
    {
        int i = Integer.parseInt(scrollBar.getAttribute("aria-valuenow"));
        int scrollPerClick = Integer.parseInt(scrollBar.getAttribute("thumbSize"));
        int end = factHeight - px;
        for(; i > end + scrollPerClick; i -= scrollPerClick)
        {
            scrollUpButton.click();
        }
        factHeight -= px;
    }

    public List<WebElement> getTableRows()
    {
        List<WebElement> elements = new ArrayList<>();

        for(int i = 1; i <= 40; i++)
        {
            elements.add(table.findElement(By.xpath("//tbody//tr[@aria-rowindex='" + i + "']")));
            scrollDown(scrollHeight);
        }
        ScrollUp(factHeight);
        return elements;
    }

    public List<String> getColumnValues()
    {
        List<String> elements = new ArrayList<>();

        for(int i = 1; i < colCount; i++)
        {
            elements.add(table.findElement(By.xpath("((//tr/th)[" + (i + 1) + "]/div/div)[1]")).getText());
        }

        return elements;
    }

    public int getColumnIndex(String columnName)
    {
        List<String> columnValues = getColumnValues();
        int index = 2;
        for(String data : columnValues)
        {
            if(data.equals(columnName))
            {
                break;
            }
            index++;
        }
        return index;
    }

    public void verifyRow(Map<String, String> map)
    {
        int index = 0;
        String key = "";
        for(String tempKey : map.keySet())
        {
            index = getColumnIndex(tempKey);
            key = tempKey;
        }

        List<WebElement> tableRows = getTableRows();

        WebElement tempCell;
        String rowIndex;
        for(WebElement tempRow : tableRows)
        {
            try
            {
                //actions.scrollToElement(tempRow).perform();
                rowIndex = tempRow.getAttribute("ariaRowIndex");
                tempCell = tempRow.findElement(By.xpath("//tr[@aria-rowindex='"
                        + rowIndex + "']//*[text()=\""
                        + map.get(key) + "\"]//ancestor::td"));
                scrollDown(scrollHeight);
            }
            catch (Exception e)
            {
                scrollDown(scrollHeight);
                continue;
            }
            int temp = Integer.parseInt(tempCell.getAttribute("ariaColIndex"));
            if(temp == index)
            {
                System.out.println("Cell at row: " + (Integer.parseInt(rowIndex) - 1));
            }
        }
    }
    
    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
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

    public WebElement getScrollDownButton()
    {
        return scrollDownButton;
    }

    public void setScrollDownButton(WebElement scrollDownButton)
    {
        this.scrollDownButton = scrollDownButton;
    }

    public WebElement getScrollUpButton()
    {
        return scrollUpButton;
    }

    public void setScrollUpButton(WebElement scrollUpButton)
    {
        this.scrollUpButton = scrollUpButton;
    }

    public int getScrollHeight() {
        return scrollHeight;
    }

    public void setScrollHeight(int scrollHeight) {
        this.scrollHeight = scrollHeight;
    }
}
