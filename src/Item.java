import java.io.Serializable;


/**
 * This class captures all the item information
 * @author Madhura
 *
 */


public class Item implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Item(Integer id, String itemName, String itemDescription,
			String itemType, Integer itemPrice, Integer quantityAvailable) {
		super();
		this.id = id;
		ItemName = itemName;
		ItemDescription = itemDescription;
		ItemType = itemType;
		ItemPrice = itemPrice;
		this.quantityAvailable = quantityAvailable;
	}
	
	public Item(){}

	private Integer id;
	
	private String ItemName;
	
	private String ItemDescription;
	
	private String ItemType;
	
	private Integer ItemPrice;
	
	private Integer quantityAvailable;

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getItemDescription() {
		return ItemDescription;
	}

	public void setItemDescription(String itemDescription) {
		ItemDescription = itemDescription;
	}

	public String getItemType() {
		return ItemType;
	}

	public void setItemType(String itemType) {
		ItemType = itemType;
	}

	public Integer getItemPrice() {
		return ItemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		ItemPrice = itemPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantityAvailable() {
		return quantityAvailable;
	}

	public void setQuantityAvailable(Integer quantityAvailable) {
		this.quantityAvailable = quantityAvailable;
	}

}
