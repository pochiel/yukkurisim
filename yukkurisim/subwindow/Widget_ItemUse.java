package subwindow;

import java.awt.image.BufferedImage;

import yukkurisim.Widget_Base;
import yukkurisim.Widget_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.ItemManager;
import yukkurisim.ImageLoader;

public class Widget_ItemUse extends Widget_Base {
	private int PageIndex;
	private BufferedImage[] myItemlist;		// 表示位置についてページ別に何を表示するか？
	private BufferedImage[] HatenaImage;		// 未知のアイテム表示
	private final int MAXPAGE = 3;			// ページマックス
	
	public Widget_ItemUse(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, BufferedImage[] arg0, int x,
			int y, Widget_Base parent, int mytype) {
		super(own, arg0, x, y, parent, mytype);
		Initialize();
	}

	public Widget_ItemUse(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent, int mytype) {
		super(own, message, x, y, parent, mytype);
		Initialize();
	}

	public void Initialize()
	{
		if(this.Get_Parent()==null)
		{
			//親ウインドウだった
			this.setPageIndex(0);		//1ページ目
		}
		else
		{
			if(this.Get_Type()==定数.ボタンID_アイテム01)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/food1.gif");
				this.myItemlist[1]=owner.getImage("item/book.gif");
				this.myItemlist[2]=owner.getImage("item/kanameishi.gif");
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==定数.ボタンID_アイテム02)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/food2.gif");
				this.myItemlist[1]=owner.getImage("item/onmyoudan.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");				
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==定数.ボタンID_アイテム03)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/ponde.gif");
				this.myItemlist[1]=owner.getImage("item/houki.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");			
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==定数.ボタンID_アイテム04)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/ponde.gif");
				this.myItemlist[1]=owner.getImage("item/scisser.gif");	// シュークリームの画像がない
				this.myItemlist[2]=owner.getImage("item/black.gif");
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==定数.ボタンID_アイテム05)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/orangejuice.gif");
				this.myItemlist[1]=owner.getImage("item/kushi.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");				
				this.setImages(myItemlist);
			}
			else if(this.Get_Type()==定数.ボタンID_アイテム06)
			{
				this.myItemlist=new BufferedImage[MAXPAGE];
				this.myItemlist[0]=owner.getImage("item/hanabi.gif");
				this.myItemlist[1]=owner.getImage("item/remiryabukuro.gif");
				this.myItemlist[2]=owner.getImage("item/black.gif");				
				this.setImages(myItemlist);				
			}
			//this.HatenaImage = owner.getImages("item/hatena.gif",1,1);
			this.HatenaImage = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_アイテムハテナ);
			
			this.setAnimate(false);
			updateItemButton();
		}
	}
	
	public void doOnMouseEvent()
	{
		int itemnum = PageIndex*7 + (this.Get_Type()-定数.ボタンID_アイテム01);	//現在表示しているアイテムnoを算出
		if(itemnum<=定数.アイテム最大番号)											//アイテムとして存在する番号なら
		{
			if((this.Get_Type()>=定数.ボタンID_アイテム01)&&(this.Get_Type()<=定数.ボタンID_アイテム06))
			{
				if(ItemManager.Get_Instance(owner).isKnown(itemnum))
				{
					if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
					{
						Widget_Manager.Get_Instance(owner).ClsMessage();
						Widget_Manager.Get_Instance(owner).DrawMessage(
																ItemManager.Get_Instance(owner).getItemExplanation(itemnum),
																this);
					}
				}
			}
		}
	}
	
	public void doClickEvent()
	{
		super.doClickEvent();

		//System.out.println("■■"+this.Get_Type());
		if(this.Get_Type()==定数.ボタンID_閉じるボタン)
		{
			this.Get_Root_Parent().setActiveToFade(false);
			return;
		}

		int itemnum = PageIndex*7 + (this.Get_Type()-定数.ボタンID_アイテム01);	//現在表示しているアイテムnoを算出
		if(itemnum<=定数.アイテム最大番号)											//アイテムとして存在する番号なら
		{
			if((this.Get_Type()>=定数.ボタンID_アイテム01)&&(this.Get_Type()<=定数.ボタンID_アイテム06))
			{
				ItemManager.Get_Instance(owner).UseItem(itemnum);
			}
		}

	}
	
	public void updateItemButton()
	{
		int itemnum = PageIndex*7 + (this.Get_Type()-定数.ボタンID_アイテム01);	//現在表示しているアイテムnoを算出
		if(itemnum<=定数.アイテム最大番号)											//アイテムとして存在する番号なら
		{
			if(ItemManager.Get_Instance(owner).isKnown(itemnum))	// 知っていれば表示
			{
				this.setImages(myItemlist);
				if(this.PageIndex!=this.getFrame())
				{
					this.setFrame(this.PageIndex);
				}
			}
			else
			{
				this.setImages(this.HatenaImage);					// 知らなければハテナ表示
			}
		}
	}
	
    public void update(long elapsedTime)
    {
    	super.update(elapsedTime);
    	if(this.Get_Parent()!=null)
    	{
    		updateItemButton();
    	}
    }

	public int getPageIndex() {
		return PageIndex;
	}

	public void setPageIndex(int pageIndex) {
		PageIndex = pageIndex;
	}
}
