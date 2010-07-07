package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Comparator;


import com.golden.gamedev.object.*;
import com.golden.gamedev.object.background.ColorBackground;

import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.yukkurisim_main;
import yukkurisim.Object_base;
import yukkurisim.FontManager;
import yukkurisim.Widget_Base;
import yukkurisim.Other_Object;

public class TitleScene extends Scene_Base {
    //private Background 	background; // 背景
    private Object_base	titlelogo;	// タイトルロゴ
    private Widget_Base	Label_game1 = new Widget_Base(owner,"はじめから",0,定数.画面高さ- 116 + 40,null);
    private Widget_Base	Label_load	= new Widget_Base(owner,"つづきから",0,定数.画面高さ- 116 + 60,null);
    //GameFont font;						//フォント
    private FontManager font;
	private int	SwitchToSchene;		//現在選択中のシーン
	
	private class ButtonWidget extends Widget_Base
	{
		// 警告画面に使用するボタンウィジェット
		public ButtonWidget(	yukkurisim_main own,
								String message,
								double x,
								double y,
								Widget_Base parent,
								int mytype) {
			super(own, message, x, y, parent, mytype);
		}

		public void doClickEvent()
		{
			if(this.Button_id==定数.ボタンID_タイトル01)
			{
				this.Get_Root_Parent().setActiveToFade(false);
				TitleScene.this.FadeOutToScene(定数.SCENE_GAME01);
				//owner.JumpSceneto(定数.SCENE_GAME01);
			}
			if(this.Button_id==定数.ボタンID_タイトル02)
			{
				this.Get_Root_Parent().setActiveToFade(false);
			}
		}		
	}
	
	private Widget_Base	dialog;
	private Widget_Base	Title_L_01;
	private Widget_Base	Title_L_02;
	private Widget_Base	Title_L_03;
	private ButtonWidget	Title_B_01;
	private ButtonWidget	Title_B_02;
	private ADV_SpriteGroup_base WarningDialog;
	
	public TitleScene(yukkurisim_main own) {		
		super(own);
		//タイトル画面表示簡略化のため、この画像はイメージローダを経由しない
    	titlelogo = new Other_Object(owner , owner.getImages("image/titlelogo.jpeg",1,1));
    	this.font = new FontManager(owner , 24);
    	this.font.setBackground(this.background);
    	titlelogo.setLocation( (定数.画面幅 - 629)/2, 0);
		owner = own;

	}
    public void initResources() {
    	this.alpha = 0.0f;
    	this.background = new ColorBackground(Color.white, 定数.画面幅, 定数.画面高さ);
    	Label_game1.setBackground(background);
    	Label_load.setBackground(background);
    	Label_game1.setActiveToFade(true);
    	// 「続きから」は、セーブデータが存在するときにのみ表示
		File objFile = new File("iurtilt.dat");
		if(objFile.exists())
		{
	    	Label_load.setActiveToFade(true);
		}
		else
		{
	    	Label_load.setActive(false);			
		}
		
    	WarningDialog = new ADV_SpriteGroup_base(owner,"WARNING");
    	dialog = new Widget_Base(	owner,
    								owner.getImages("image/widget/dialogwindow.GIF", 1, 1),
    								10,
    								10,
    								null);
    	Title_L_01 = new Widget_Base(	owner,
										"セーブデータが既に存在しています。",
										10,
										30,
										dialog);
    	Title_L_02 = new Widget_Base(	owner,
										"このままスタートするとセーブデータ",
										10,
										74,
										dialog);
    	Title_L_03 = new Widget_Base(	owner,
										"が上書きされますが、よろしいですか？",
										10,
										118,
										dialog);
    	Title_B_01 = new ButtonWidget(	owner,
										"いいよ",
										30,
										165,
										dialog,
										定数.ボタンID_タイトル01);
    	Title_B_02 = new ButtonWidget(	owner,
										"だめだよ",
										70,
										165,
										dialog,
										定数.ボタンID_タイトル02);
    	Title_L_01.SetFontsize(15);
    	Title_L_02.SetFontsize(15);
    	Title_L_03.SetFontsize(15);

    	dialog.Mount_widget(Title_L_01, 10, 30);
    	dialog.Mount_widget(Title_L_02, 10, 74);
    	dialog.Mount_widget(Title_L_03, 10, 118);
    	dialog.Mount_widget(Title_B_01, 30, 165);
    	dialog.Mount_widget(Title_B_02, 100, 165);
    	
    	WarningDialog.add(dialog);
    	WarningDialog.add(Title_L_01);
    	WarningDialog.add(Title_L_02);
    	WarningDialog.add(Title_L_03);
    	WarningDialog.add(Title_B_01);
    	WarningDialog.add(Title_B_02);
    	   	
    	dialog.setActive(false);

       	/************* Comparator セット **************/
    	WarningDialog.setComparator(
    			new Comparator(){
    				public int compare(Object o1, Object o2){
    					Object_base	s1 = (Object_base) o1,
    								s2 = (Object_base) o2;
    					return (int)(s1.Get_Id() - s2.Get_Id() );
    				}
    			}
    	);
    	WarningDialog.getScanFrequence().setActive(false);
    	
    	this.FadeInScreen();

    }
    
	public void update(long elapsedTime)
	{
		super.update(elapsedTime);
		background.update(elapsedTime);
		titlelogo.update(elapsedTime);
		WarningDialog.update(elapsedTime);
		WarningDialog.doClickEvent();
		
		Label_game1.update(elapsedTime);
		Label_load.update(elapsedTime);
		
		if((Label_game1.isClicked())&&(!(dialog.isActive())))
		{
			// はじめから
			File objFile = new File("iurtilt.dat");

			if(objFile.exists())
			{
				// ファイルが存在したら警告する
				dialog.setActiveToFade(true);
			}
			else
			{
				this.FadeOutToScene(定数.SCENE_GAME01);
				//owner.JumpSceneto(定数.SCENE_GAME01);
			}
		}
		
		else if(Label_load.isClicked())
		{
			this.FadeOutToScene(定数.SCENE_LOAD);
			//owner.JumpSceneto(定数.SCENE_LOAD);		//ロードシーンへジャンプ
		}
	}
	
	public void render(Graphics2D g)
	{
		super.render(g);
		//background.render(g);

		titlelogo.render(g);

		Label_game1.render(g);
		Label_load.render(g);
		
		WarningDialog.render(g);
		
	}
}
