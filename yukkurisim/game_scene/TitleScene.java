package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Comparator;


import com.golden.gamedev.object.background.ColorBackground;

import yukkurisim.ADV_SpriteGroup_base;
import yukkurisim.yukkurisim_main;
import yukkurisim.Object_base;
import yukkurisim.FontManager;
import yukkurisim.Widget_Base;
import yukkurisim.Other_Object;

public class TitleScene extends Scene_Base {
    //private Background 	background; // �w�i
    private Object_base	titlelogo;	// �^�C�g�����S
    private Widget_Base	Label_game1 = new Widget_Base(owner,"�͂��߂���",0,�萔.��ʍ���- 116 + 40,null);
    private Widget_Base	Label_load	= new Widget_Base(owner,"�Â�����",0,�萔.��ʍ���- 116 + 60,null);
    //GameFont font;						//�t�H���g
    private FontManager font;
	private int	SwitchToSchene;		//���ݑI�𒆂̃V�[��
	
	private class ButtonWidget extends Widget_Base
	{
		// �x����ʂɎg�p����{�^���E�B�W�F�b�g
		public ButtonWidget(	yukkurisim_main own,
								String message,
								double x,
								double y,
								Widget_Base parent,
								int mytype) {
			super(own, message, x, y, parent, mytype);
		}

		@Override
		public void doClickEvent()
		{
			if(this.Button_id==�萔.�{�^��ID_�^�C�g��01)
			{
				this.Get_Root_Parent().setActiveToFade(false);
				owner.setLoadEndedScene(�萔.SCENE_GAME01);			// ���[�f�B���O��ʏI����̈ړ���V�[���Z�b�g
				TitleScene.this.FadeOutToScene(�萔.SCENE_LOADING);// ���[�f�B���O��ʕ\��
				//owner.JumpSceneto(�萔.SCENE_GAME01);
			}
			if(this.Button_id==�萔.�{�^��ID_�^�C�g��02)
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
		//�^�C�g����ʕ\���ȗ����̂��߁A���̉摜�̓C���[�W���[�_���o�R���Ȃ�
    	titlelogo = new Other_Object(owner , owner.getImages("image/titlelogo.jpeg",1,1));
    	this.font = new FontManager(owner , 24);
    	this.font.setBackground(this.background);
    	titlelogo.setLocation( (�萔.��ʕ� - 629)/2, 0);
		owner = own;

	}
    @Override
	public void initResources() {
    	this.alpha = 0.0f;
    	this.background = new ColorBackground(Color.white, �萔.��ʕ�, �萔.��ʍ���);
    	Label_game1.setBackground(background);
    	Label_load.setBackground(background);
    	Label_game1.setActiveToFade(true);
    	// �u��������v�́A�Z�[�u�f�[�^�����݂���Ƃ��ɂ̂ݕ\��
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
										"�Z�[�u�f�[�^�����ɑ��݂��Ă��܂��B",
										10,
										30,
										dialog);
    	Title_L_02 = new Widget_Base(	owner,
										"���̂܂܃X�^�[�g����ƃZ�[�u�f�[�^",
										10,
										74,
										dialog);
    	Title_L_03 = new Widget_Base(	owner,
										"���㏑������܂����A��낵���ł����H",
										10,
										118,
										dialog);
    	Title_B_01 = new ButtonWidget(	owner,
										"������",
										30,
										165,
										dialog,
										�萔.�{�^��ID_�^�C�g��01);
    	Title_B_02 = new ButtonWidget(	owner,
										"���߂���",
										70,
										165,
										dialog,
										�萔.�{�^��ID_�^�C�g��02);
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

       	/************* Comparator �Z�b�g **************/
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
    
	@Override
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
			// �͂��߂���
			File objFile = new File("iurtilt.dat");

			if(objFile.exists())
			{
				// �t�@�C�������݂�����x������
				dialog.setActiveToFade(true);
			}
			else
			{
				owner.setLoadEndedScene(�萔.SCENE_GAME01);			// ���[�f�B���O��ʏI����̈ړ���V�[���Z�b�g
				this.FadeOutToScene(�萔.SCENE_LOADING);			// ���[�f�B���O��ʕ\��

				//this.FadeOutToScene(�萔.SCENE_GAME01);
				//owner.JumpSceneto(�萔.SCENE_GAME01);
			}
		}
		
		else if(Label_load.isClicked())
		{
			owner.setLoadEndedScene(�萔.SCENE_LOAD);			// ���[�f�B���O��ʏI����̈ړ���V�[���Z�b�g
			this.FadeOutToScene(�萔.SCENE_LOADING);			// ���[�f�B���O��ʕ\��

			//this.FadeOutToScene(�萔.SCENE_LOAD);
			//owner.JumpSceneto(�萔.SCENE_LOAD);		//���[�h�V�[���փW�����v
		}
	}
	
	@Override
	public void render(Graphics2D g)
	{
		super.render(g);

		titlelogo.render(g);

		Label_game1.render(g);
		Label_load.render(g);
		
		WarningDialog.render(g);
		
	}
}
