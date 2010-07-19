package subwindow;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import com.golden.gamedev.object.Sprite;

import yukkurisim.Object_base;
import yukkurisim.Physical_Law_Facade;
import yukkurisim.Widget_Base;
import yukkurisim.Widget_Checkbox;
import yukkurisim.Widget_Manager;
import yukkurisim.yukkurisim_main;
import yukkurisim.yukkuri_base;
import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.ImageLoader;
import gamestatus.GameInfoManager;
import gamestatus.SyuzokuInfoManager;

public class Widget_Action extends Widget_Base {
	private int[] Syuzoku_List;
	private int SyuzokuIndex = 0;
	private int[] Cooperative_List;		// 協調性配列
	private int[] Hunting_List;			// 狩り能力配列
	private SyuzokuInfoManager 種族 = new SyuzokuInfoManager();
	
	private ADV_SpriteGroup_base Checkboxs;
    private Widget_Checkbox	ActionWindow_C_01_kari;	// チェックボックス１_狩り
    private Widget_Checkbox	ActionWindow_C_02_farm;	// チェックボックス１_農業
    private Widget_Checkbox	ActionWindow_C_03_play;	// チェックボックス１_遊ぶ
    private boolean			Last_State[] = {false,false,false};

	/** その他画像データ表示用画像データ参照 **/
	private BufferedImage[] reimu_image = null;		// れいむのイメージ
	private BufferedImage[] marisa_image = null;		// まりさのイメージ

	public Widget_Action(yukkurisim_main own) {
		super(own);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0) {
		super(own, arg0);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, double arg0, double arg1) {
		super(own, arg0, arg1);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y) {
		super(own, arg0, x, y);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0, double x,
			double y, Widget_Base parent) {
		super(own, arg0, x, y, parent);
		Initialize();
	}

	public Widget_Action(yukkurisim_main own, String message, double x,
			double y, Widget_Base parent) {
		super(own, message, x, y, parent);
		Initialize();
	}
	
	/* 通常用いるコンストラクタはこれ */
	public Widget_Action(yukkurisim_main own, BufferedImage[] arg0, double x,
		double y, Widget_Base parent,int mytype) {
		super(own, arg0, x, y, parent);
		this.Button_id = mytype;
		Initialize();
	}
	
	public Widget_Action(yukkurisim_main own,
			String message,
			double x,
			double y ,
			Widget_Base parent,
			int mytype)
	{
		super(own,message,x,y,parent,mytype);
		Initialize();
	}
	public void doClickEvent()
	{
		System.out.println("■■WidgetActionのdoclickevent");
	
		super.doClickEvent();
		
		if(Button_id==定数.ボタンID_閉じるボタン)
		{
			if( ((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==-1 )		// チェックボックスが一つだけ押されている状態でない
			{
				Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("どれか一つを選択してください。");
				return;
			}
			else if(((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==1)
			{
				// 狩り
				GameInfoManager.Get_Instance().setPlayPartState(SyuzokuIndex,定数.アクション_狩り);
				this.Get_Root_Parent().setActiveToFade(false);	// 行動ウインドウ自体のインアクティベート
				PhLaw.Change_Part();						// パート移動
				this.setActiveToFade(false);		
			}
			else if(((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==2)
			{
				boolean farmMaded = false;
				
				// 農地が作られているかチェック
				for(int i=0;i<定数.画面横セル幅;i++)
				{
					for(int j=0;j<定数.画面縦セル幅;j++)
					{
						if(Physical_Law_Facade.Get_Instance(owner).Get_ObjectManager().Get_obj_type(i, j)==定数.TYPE_農地object)
						{
							farmMaded = true;
						}
					}
				}
				
				//　農地がすでに作られていれば
				if( farmMaded )
				{
					GameInfoManager.Get_Instance().setPlayPartState(SyuzokuIndex,定数.アクション_農業);
					// 農業
					this.Get_Root_Parent().setActiveToFade(false);	// 行動ウインドウ自体のインアクティベート
					PhLaw.Change_Part();						// パート移動
					this.setActiveToFade(false);
				}
				else
				{
					Widget_Manager.Get_Instance(owner).Popup_Dialog_Window("農地を設置してください！");
					return;
				}
			}
			else if(((Widget_Action)this.Get_Root_Parent()).Get_Checkbox_Propery()==3)
			{
				// プレイ開始
				GameInfoManager.Get_Instance().setPlayPartState(SyuzokuIndex,定数.アクション_遊ぶ);
				this.Get_Root_Parent().setActiveToFade(false);	// 行動ウインドウ自体のインアクティベート
				PhLaw.Change_Part();						// パート移動
				this.setActiveToFade(false);
			}

		}
	}

	/**
	 * 行動選択ウインドウで使用されるチェックボックスが
	 * 正しい（ 一つだけ選択されている）状態にあるかチェック
	 * @return 各チェックボックスの数値を返す。
	 * １→狩り　２→農業　３→遊ぶ　－１→不正
	 */
	public int Get_Checkbox_Propery()
	{
		if(this.Get_Parent()!=null)
		{	// 親ウインドウ以外で実行された
			return -1;
		}
		else
		{
			int ans = ActionWindow_C_01_kari.Get_Value()?1:0;
			ans += ActionWindow_C_02_farm.Get_Value()?1:0;
			ans += ActionWindow_C_03_play.Get_Value()?1:0;

			if(ans==1)
			{
				if(ActionWindow_C_01_kari.Get_Value())
				{
					return 1;
				}
				else if(ActionWindow_C_02_farm.Get_Value())
				{
					return 2;
				}
				else
				{
					return 3;
				}
			}
			else
			{
				return -1;
			}
		}
	}
	
	/**
	 * 引数として指定されたチェックボックス以外のチェックボックスをfalseとします。
	 * ルートウインドウ以外で呼び出しても意味ないです。
	 * @param c
	 */
	public void Set_Onlyone_Checkbox()
	{
		if(this.Get_Parent()==null)
		{
			boolean tmp[]=new boolean[3];
			tmp[0]=ActionWindow_C_01_kari.Get_Value();
			tmp[1]=ActionWindow_C_02_farm.Get_Value();
			tmp[2]=ActionWindow_C_03_play.Get_Value();
			
			if(	!(
					(tmp[0]==Last_State[0])&&
					(tmp[1]==Last_State[1])&&
					(tmp[2]==Last_State[2])
				)	)	// 全て一致しない（どこかが違っている場合）
			{
				// 排他的論理和を格納
				//System.out.println("last→"+Last_State[0]+Last_State[1]+Last_State[2]+"  tmp→"+tmp[0]+tmp[1]+tmp[2]);
				ActionWindow_C_01_kari.Set_Value(tmp[0]^Last_State[0]);
				ActionWindow_C_02_farm.Set_Value(tmp[1]^Last_State[1]);
				ActionWindow_C_03_play.Set_Value(tmp[2]^Last_State[2]);
				
				Last_State[0]=ActionWindow_C_01_kari.Get_Value();
				Last_State[1]=ActionWindow_C_02_farm.Get_Value();
				Last_State[2]=ActionWindow_C_03_play.Get_Value();
					
			}
		}
	}
	
	public void Initialize()
	{
		this.Syuzoku_List = new int[定数.TYPE_キャラクター系タイプ総数];
		this.Cooperative_List = new int[定数.TYPE_キャラクター系タイプ総数];
		this.Hunting_List = new int[定数.TYPE_キャラクター系タイプ総数];
		if(this.Get_Parent()==null)
		{
			this.ResetSyuzokuList();			// 種族リストのリセット(ルートのみ)
			this.Reset_Cooperate_AVG_List();	// 協調性平均リストのリセット
			this.Reset_Hunting_AVG_List();		// 狩の能力のリセット
			
	       	this.ActionWindow_C_01_kari = new Widget_Checkbox(		owner,this);
	       	this.ActionWindow_C_02_farm = new Widget_Checkbox(		owner,this);
	       	this.ActionWindow_C_03_play = new Widget_Checkbox(		owner,this);
	       	this.Checkboxs = new ADV_SpriteGroup_base(owner,"WIDGET_ACTION_CHECKBOX");
	       	this.Mount_widget(ActionWindow_C_01_kari, 20, 120);
	       	this.Mount_widget(ActionWindow_C_02_farm, 100, 120);
	       	this.Mount_widget(ActionWindow_C_03_play, 180, 120);
	       	this.Checkboxs.add(ActionWindow_C_01_kari);
	       	this.Checkboxs.add(ActionWindow_C_02_farm);
	       	this.Checkboxs.add(ActionWindow_C_03_play);
	       	
	       	/************* Comparator セット **************/
	    	this.Checkboxs.setComparator(
	    			new Comparator(){
	    				public int compare(Object o1, Object o2){
	    					Object_base	s1 = (Object_base) o1,
	    								s2 = (Object_base) o2;
	    					return (int)(s1.Get_Id() - s2.Get_Id() );
	    				}
	    			}
	    	);
	    	
	    	/************* このグループは非アクティブなオブジェクトを削除しない **********/
	    	this.Checkboxs.getScanFrequence().setActive(false);

		}
		else
		{
			this.Syuzoku_List=((Widget_Action)this.Get_Root_Parent()).Get_SyuzokuList();			
			this.Cooperative_List=((Widget_Action)this.Get_Root_Parent()).Get_Cooperate_AVG_List();
			this.Hunting_List=((Widget_Action)this.Get_Root_Parent()).Get_Hunting_AVG_List();
			this.ResetIndex();
		}
		// その他画像表示用画像データ初期化処理
		if(Button_id==定数.その他ID_画像表示)
		{
			//this.reimu_image = owner.getImages("image/reimuwait.gif", 1, 6);
			this.reimu_image = ImageLoader.Get_Instance(owner).getBufferedImage(定数.画像番号_ゆっくりれいむ待つ);
			
			this.setAnimate(true);				// アニメーションさせる
			this.setLoopAnim(true);			// ループさせる
		}
	}

	/**
	 * 種族リストをリセットします。
	 * 処理はルートウィジェットのみ行う。
	 */
	private void ResetSyuzokuList()
	{
		// ルートウィジェットのみ
		if(this.Get_Parent()==null)
		{
			Sprite[]	msprites = PhLaw.Get_ObjectManager().getSprites();
			int			msize = PhLaw.Get_ObjectManager().getSize();
			Object_base temp;
	
			for(int i = 0;i<定数.TYPE_キャラクター系タイプ総数;i++)
			{
				// 初期化
				this.Syuzoku_List[i] = 0;
			}
	
			for(int i=(msize-1);i>=0;i--){
				temp = (Object_base)msprites[i];
				
				if( temp.isActive()&&(temp.Get_Type()<定数.TYPE_object_キャラクタと建築物番号敷居値)){
					this.Syuzoku_List[temp.Get_Type()-100]++;	// 種族識別定数は100からスタート
				}
			}
		}
	}
	/**
	 * 
	 */
	private void ResetIndex()
	{
		int i = 0;
		while(this.Syuzoku_List[i]>0)
		{
			this.SyuzokuIndex=i;
			break;
		}
	}
	
	/*
	 * 種族リストを返す。 nullを返した場合、参照がおかしい。
	 */
	public int[] Get_SyuzokuList()
	{
		if(this.Get_Parent()==null)
		{
			this.ResetSyuzokuList();
			return this.Syuzoku_List;
		}
		return null;
	}
	
	/**
	 * 協調性の平均を求める
	 * @return
	 */
	public int[] Get_Cooperate_AVG_List()
	{
		if(this.Get_Parent()==null)
		{
			this.Reset_Cooperate_AVG_List();
			return this.Cooperative_List;
		}
		return null;
		
	}
	
	public void Reset_Cooperate_AVG_List()
	{
		// ルートウィジェットのみ
		if(this.Get_Parent()==null)
		{
			Sprite[]	msprites = PhLaw.Get_ObjectManager().getSprites();
			int			msize = PhLaw.Get_ObjectManager().getSize();
			int[] num_cnt = new int[定数.TYPE_キャラクター系タイプ総数];
			yukkuri_base temp;
	
			for(int i = 0;i<定数.TYPE_キャラクター系タイプ総数;i++)
			{
				// 初期化
				this.Cooperative_List[i] = 0;
			}
	
			for(int i=(msize-1);i>=0;i--){
				if( ((Object_base)msprites[i]).isCharactor())
				{
					temp = (yukkuri_base)msprites[i];
					
					if(temp.isActive()&&(temp.Get_Type()<定数.TYPE_object_キャラクタと建築物番号敷居値)){
						// 種族識別定数は100からスタート
						num_cnt[temp.Get_Type()-100]++;
						this.Cooperative_List[temp.Get_Type()-100]+=temp.Get_Cooperative();	
					}
				}
			}
			
			for(int i = 0;i<定数.TYPE_キャラクター系タイプ総数;i++)
			{
				if(num_cnt[i]!=0)
				{
					Cooperative_List[i] = (int)(Cooperative_List[i]/num_cnt[i]);
				}
			}
		}
	}

	/**
	 * 協調性の平均を求める
	 * @return
	 */
	public int[] Get_Hunting_AVG_List()
	{
		if(this.Get_Parent()==null)
		{
			this.Reset_Hunting_AVG_List();
			return this.Hunting_List;
		}
		return null;
		
	}
	
	public void Reset_Hunting_AVG_List()
	{
		// ルートウィジェットのみ
		if(this.Get_Parent()==null)
		{
			Sprite[]	msprites = PhLaw.Get_ObjectManager().getSprites();
			int			msize = PhLaw.Get_ObjectManager().getSize();
			int[] 		num_cnt = new int[定数.TYPE_キャラクター系タイプ総数];
			yukkuri_base temp;
	
			for(int i = 0;i<定数.TYPE_キャラクター系タイプ総数;i++)
			{
				// 初期化
				this.Hunting_List[i] = 0;
			}
	
			for(int i=(msize-1);i>=0;i--){
				if( ((Object_base)msprites[i]).isCharactor())
				{
					temp = (yukkuri_base)msprites[i];
					
					if(temp.isActive()&&(temp.Get_Type()<定数.TYPE_object_キャラクタと建築物番号敷居値)){
						// 種族識別定数は100からスタート
						num_cnt[temp.Get_Type()-100]++;
						this.Hunting_List[temp.Get_Type()-100]+=temp.Get_Hunting();	
					}
				}
			}
			
			for(int i = 0;i<定数.TYPE_キャラクター系タイプ総数;i++)
			{
				if(num_cnt[i]!=0)
				{
					Hunting_List[i] = (int)(Hunting_List[i]/num_cnt[i]);
				}
			}
		}
	}

	
	
	public void update(long elapsedTime)
    {
		/*if(Button_id==定数.ボタンID_閉じるボタン)
		{
			/** デバッグ **/
			/*System.out.println("■デバッグ→"+this.isClicked());
		}*/
		if(this.Get_Parent()==null)
		{
			this.Checkboxs.update(elapsedTime);
			this.Checkboxs.doClickEvent();
    		Set_Onlyone_Checkbox();
		}
    	if(Button_id==定数.ラベルID_アクション_種族名)
    	{
    		this.Set_Mymessage("しゅぞく　："+種族.Get_SyuzokuName(SyuzokuIndex+100)+"のへいきん");
    	}
    	else if(Button_id==定数.ラベルID_アクション_農業)
    	{
    		this.Set_Mymessage("のうぎょう："+種族.Get_rank_Cooperative(Cooperative_List[SyuzokuIndex]));
    	}
    	else if(Button_id==定数.ラベルID_アクション_狩り)
    	{
    		this.Set_Mymessage("かり　　　："+種族.Get_rank_kari(Hunting_List[SyuzokuIndex]));
    	}
    	else if(Button_id==定数.その他ID_画像表示)
    	{
    		if((SyuzokuIndex+100==定数.TYPE_ゆっくりobject)&&(this.getImages()!=reimu_image))
    		{
    			this.setImages(reimu_image);
    		}
    	}
    	super.update(elapsedTime);
    }
	
    public void render(Graphics2D g)
    {
    	super.render(g);
    	if(this.Get_Parent()==null)
    	{
    		this.Checkboxs.render(g);
    	}
    	//System.out.println("this→"+this+"  this.alp→"+this.alpha+"   this.isact→"+this.isActive());
    }
    
    public void doOnMouseEvent()
    {
    	if(Widget_Manager.Get_Instance(owner).getMessenger()!=this)
    	{
    		if(Button_id==定数.ラベルID_アクション_説明_狩り)
    		{
    			Widget_Manager.Get_Instance(owner).ClsMessage();
    			Widget_Manager.Get_Instance(owner).DrawMessage("この種族を狩りに出すよ。何か見つけてくるかも？", this);
    		}
    		else if(Button_id==定数.ラベルID_アクション_説明_農業)
        	{
    			Widget_Manager.Get_Instance(owner).ClsMessage();
    			Widget_Manager.Get_Instance(owner).DrawMessage("この種族に畑の世話をさせよう。おいしい野菜ができるかな？", this);
        	}
        	else if(Button_id==定数.ラベルID_アクション_説明_遊び)
        	{
    			Widget_Manager.Get_Instance(owner).ClsMessage();
    			Widget_Manager.Get_Instance(owner).DrawMessage("この種族は今日は休みだ！ゆっくり遊んでいってね！", this);
        	}
    	}
    }
}
