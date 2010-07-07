package subwindow;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.golden.gamedev.object.Sprite;

import gamestatus.GameInfoManager;

import yukkurisim.Cursor_Manager;
import yukkurisim.Farm_Object;
import yukkurisim.Object_base;
import yukkurisim.Widget_Manager;
import yukkurisim.Widget_Base;
import yukkurisim.yukkurisim_main;
import yukkurisim.Tree_Object;
import yukkurisim.House_Object;
import yukkurisim.MapTileManager;

public class Widget_Build extends Widget_Base {
    private Cursor_Manager	OwnCursor;				// カーソル
    private int ItemIndex = 0;						// 何を選択しているか？
    private int PageIndex = 1;						// 何ページを参照しているか
    private String CostLabel;						// 費用ラベルで何を表示するか？親だけが保持

    public Widget_Build(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Build(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Build(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Build(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Build(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Build(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent,int mytype) {
		super(own, message, x, y, parent);
		this.Button_id=mytype;
		Initialize();
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public Widget_Build(yukkurisim_main own, BufferedImage[] arg0, int x, int y, Widget_Build parent, int mytype) {
		super(own, arg0, x, y, parent);
		this.Button_id = mytype;
		Initialize();
	}
	
	public void Initialize()
	{
		OwnCursor = Cursor_Manager.Get_Instance(owner);		// カーソル

		// 正直、あんまりスマートなコードじゃないと思うけど・・・。		
		if(this.Get_Parent()==null)
		{
			CostLabel = "費用　：";
		}
	}
	
	public void doOnMouseEvent()
	{
		if(Button_id==定数.ボタンID_木ボタン)
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("おいしそうなリンゴの生る木です。", this);
				((Widget_Build)this.Get_Root_Parent()).setCostLabel("費用　：" + GameInfoManager.Get_Instance().getCost(定数.TYPE_木object));
			}
		}
		else if( Button_id==定数.ボタンID_家ボタン)
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("ゆっくりたちの住むおうちです。", this);
				((Widget_Build)this.Get_Root_Parent()).setCostLabel("費用　：" + GameInfoManager.Get_Instance().getCost(定数.TYPE_家object));
			}			
		}
		else if( Button_id==定数.ボタンID_農地ボタン)
		{
			if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("ゆっくりたちの農園です。", this);
				((Widget_Build)this.Get_Root_Parent()).setCostLabel("費用　：" + GameInfoManager.Get_Instance().getCost(定数.TYPE_農地object));
			}			
		}
		else
		{
			((Widget_Build)this.Get_Root_Parent()).setCostLabel("費用　：");
		}
	}
	
	public void doClickEvent()
	{
		//System.out.println("Widgetbuildのdoclickevent");

		if(Button_id==定数.ボタンID_木ボタン)
		{
			System.out.println("木ボタンおされたお");
			OwnCursor.SetCursorActivate(this, true, 定数.カーソル処理_ビルドウインドウ01_木,1,1);	// カーソルを表示
			this.Get_Root_Parent().setActiveToFade(false);	// ビルドウインドウ自体のインアクティベート
		}
		else if( Button_id==定数.ボタンID_家ボタン)
		{
			System.out.println("家ボタンおされたお");
			OwnCursor.SetCursorActivate(this, true, 定数.カーソル処理_ビルドウインドウ01_家,3,2);	// カーソルを表示
			this.Get_Root_Parent().setActiveToFade(false);	// ビルドウインドウ自体のインアクティベート
			
		}
		else if( Button_id==定数.ボタンID_農地ボタン)
		{
			System.out.println("農地ボタンおされたお");
			OwnCursor.SetCursorActivate(this, true, 定数.カーソル処理_ビルドウインドウ01_農地,1,1);	// カーソルを表示
			this.Get_Root_Parent().setActiveToFade(false);	// ビルドウインドウ自体のインアクティベート
			
		}
		else if( Button_id==定数.ボタンID_閉じるボタン)
		{
			System.out.println("閉じるボタン");
			this.Get_Root_Parent().setActiveToFade(false);	// ビルドウインドウ自体のインアクティベート
			PhLaw.Change_Part();						// パート移動
			this.setActiveToFade(false);
		}
		else if( Button_id==定数.ボタンID_撤去ボタン )
		{
			Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("撤去ボタンは未実装です。");		// 17文字
			System.out.println("撤去ボタン");
		}
		else
		{
			System.out.println("押されたけど機ボタンじゃないお→"+Button_id);			

		}
	}
    public void update(long elapsedTime)
    {
    	if(Button_id==定数.ラベルID_建築_所持金)
    	{	//所持金表示
    		this.Set_Mymessage("所持金："+GameInfoManager.Get_Instance().GetCash()+"円");
    	}
    	if(Button_id==定数.ラベルID_建築_費用)
    	{
    		// 各ボタンのOnMouseRideイベントからの費用メッセージの受け渡しは
    		// root親を経由して行う。（キモチワルイコード 2010グランプリ
    		this.Set_Mymessage(((Widget_Build)this.Get_Root_Parent()).getCostLabel());
    	}
    	super.update(elapsedTime);
    }

    public void render(Graphics2D g) {
    	super.render(g);

    	if(this.ItemIndex!=0)
    	{
    		// 何らかのアイテムが選択されていれば
    		
    	}
    }

	public void Comeback_From_Cursor(int x,int y,int s)
	{
		GameInfoManager gman = GameInfoManager.Get_Instance();

		System.out.println("widget_buildに戻ってきました。x->"+x+"  y->"+y);
		if( s==定数.カーソル処理_ビルドウインドウ01_木 )
		{
			if(this.PhLaw.Get_TileManager().Check_able_to_Build(x, y,1,1))
			{
				if(gman.getCost(定数.TYPE_木object)>gman.GetCash())
				{
					Widget_Manager.Get_Instance(owner).ClsMessage();
					Widget_Manager.Get_Instance(owner).DrawMessage("お金が足りないよ！",this);					
				}
				else
				{
					gman.SetCash( gman.GetCash() - gman.getCost(定数.TYPE_木object) );	// お金を消費
					PhLaw.Get_ObjectManager().add(new Tree_Object(owner,x,y),定数.TYPE_木object);
				}
			}
			else
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("そこには設置できないよ！",this);
			}			
		}
		else if( s==定数.カーソル処理_ビルドウインドウ01_農地 )
		{
			if(this.PhLaw.Get_TileManager().Check_able_to_Build(x, y,1,1))
			{
				if(gman.getCost(定数.TYPE_農地object)>gman.GetCash())
				{
					Widget_Manager.Get_Instance(owner).ClsMessage();
					Widget_Manager.Get_Instance(owner).DrawMessage("お金が足りないよ！",this);					
				}
				else
				{
					gman.SetCash( gman.GetCash() - gman.getCost(定数.TYPE_農地object) );	// お金を消費
					PhLaw.Get_ObjectManager().add(new Farm_Object(owner,x,y),定数.TYPE_農地object);
				}
			}
			else
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("そこには設置できないよ！",this);
			}			
		}
		else if ( s==定数.カーソル処理_ビルドウインドウ01_家 )
		{
			if(this.PhLaw.Get_TileManager().Check_able_to_Build(x, y, 3, 2))
			{
				if(gman.getCost(定数.TYPE_家object)>gman.GetCash())
				{
					Widget_Manager.Get_Instance(owner).ClsMessage();
					Widget_Manager.Get_Instance(owner).DrawMessage("お金が足りないよ！",this);										
				}
				else
				{
					gman.SetCash( gman.GetCash() - gman.getCost(定数.TYPE_家object) );	// お金を消費
					PhLaw.Get_ObjectManager().add(new House_Object(owner,x,y),定数.TYPE_家object); 	// HouseObject側で行う
				}
			}
			else
			{
				Widget_Manager.Get_Instance(owner).ClsMessage();
				Widget_Manager.Get_Instance(owner).DrawMessage("そこには設置できないよ！",this);
			}
		}
		this.Get_Root_Parent().setActiveToFade(true);	// ビルドウインドウ自体のアクティベート

	}

	public String getCostLabel() {
		return CostLabel;
	}

	public void setCostLabel(String costLabel) {
		CostLabel = costLabel;
	}
}
