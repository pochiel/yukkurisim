package game_scene;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import yukkurisim.ImageLoader;
import yukkurisim.Object_base;
import yukkurisim.OtherLoader;
import yukkurisim.Other_Object;
import yukkurisim.Widget_Base;
import yukkurisim.yukkurisim_main;

public class Loading extends Scene_Base {

	/*
	 * �q�[�v��̃f�[�^�ɃA�N�Z�X����ۂ́A����1�̒��ӓ_��
	 * �X���b�h����q�[�v�ւ̃A�N�Z�X���ɂ́A�L���b�V�������p�����Ƃ����_�ł��B
	 * �S�ẴX���b�h�̓L���b�V���̈�Ƃ������̂�����
	 * �q�[�v�̃f�[�^���Q�Ƃ���ۂ�A�q�[�v�̃f�[�^���X�V����ۂ̑S�Ă̑���ɂ����āA�L���b�V�����g���܂��B
	 * �܂�A�q�[�v��̃f�[�^���Q�Ƃ��Ă��A�L���b�V����̌Â��f�[�^���Q�Ƃ��Ă���\��������A
	 * �q�[�v��̃f�[�^���X�V���Ă��A�L���b�V����̃f�[�^���X�V���Ă��邾����
	 * �ʂ̃X���b�h����́A�X�V�O�̌Â��f�[�^�������Ă���\��������܂��B
	 * ���̃L���b�V���́A����^�C�~���O�Ńq�[�v�Ɠ������Ƃ�
	 * �ŐV�̏�ԂɂȂ�܂����A���̃^�C�~���O��m�邱�Ƃ͂ł��܂���B 
	 */
	
	private int LoadExitedScene;
	private boolean 		nowLoading;
	private boolean 		nowFading;
	
	private Object_base	RotateYukkuri;		// ��]���Ă���������
	private Widget_Base	NowLoadingText;		// �\���F����...�̕���
	private boolean		isFadeOutNow;		// �������t�F�[�h�A�E�g���Ă��邩�𐧌䂷��
	private BufferedImage[] yImage;			// �������̉摜
	private int			rotatesize;			// ��]�p�x(360�x�@)
	
	private ImageLoader 	iLoadertmp;
	private OtherLoader	otmp;
	/** ���[�f�B���O���ɕ\������N���X **/
	public Loading(yukkurisim_main own) {
		super(own);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	
	@Override
	public void render(Graphics2D g)
	{
		super.render(g);
		NowLoadingText.render(g);
		RotateYukkuri.render(g);
	}
	
	@Override
	public void initResources() 
	{
		super.initResources();
		LoadExitedScene = -1;
		nowLoading = false;
		nowFading = false;
		rotatesize = 0;
		yImage = owner.getImages("image/reimuwait.gif",6,1);
		
		RotateYukkuri = new Other_Object(owner , yImage);
		NowLoadingText = new Widget_Base(	owner,
				"�\���F����...",
				0,
				0,
				null);
		NowLoadingText.setLocation(�萔.��ʕ� - NowLoadingText.getWidth() - 10, 5 + ( RotateYukkuri.getHeight()/2 ) - ( NowLoadingText.getHeight()/2 ));
		NowLoadingText.setAlpha(0);
		NowLoadingText.setFontColor(Color.white);		// �����F�͔�
		NowLoadingText.setBackground(this.background);
		NowLoadingText.setActive(true);
		isFadeOutNow = false;							// �������t�F�[�h�A�E�g���Ă��邩�𐧌䂷��
		RotateYukkuri.setLocation(NowLoadingText.getX() - RotateYukkuri.getWidth() , 5);
		RotateYukkuri.setAnimate(true);
		RotateYukkuri.setBackground(this.background);

	}
	
	@Override
	public void update(long elapsedTime)
	{
		AffineTransform at = new AffineTransform();
		super.update(elapsedTime);
		if(isFadeOutNow)
		{
			NowLoadingText.setAlpha(NowLoadingText.getAlpha() - 0.02f);
			if( NowLoadingText.getAlpha() <= 0.0f )
			{
				NowLoadingText.setAlpha(0.0f);
				isFadeOutNow = false;
			}
			
			at.setToRotation(Math.toRadians(rotatesize+=2),(yImage[1].getWidth() / 2),(yImage[1].getHeight()/2));	// ��]�̃Z�b�g
			if( rotatesize >= 360 )
			{
				rotatesize = 0;
			}
			AffineTransformOp atOp = new AffineTransformOp(at, null);

			BufferedImage[] revimg = new BufferedImage[yImage.length];
			for(int i=0;i<yImage.length;i++)
			{
				revimg[i] = atOp.filter(yImage[i], null);	// ���ۂɉ摜�ϊ����s���Ă�̂̓R�R
			}
			RotateYukkuri.setImages(revimg);
		}
		else
		{
			NowLoadingText.setAlpha(NowLoadingText.getAlpha() + 0.02f);
			if( NowLoadingText.getAlpha() >= 1.0f )
			{
				NowLoadingText.setAlpha(1.0f);
				isFadeOutNow = true;
			}
			
			at.setToRotation(Math.toRadians(rotatesize+=2),(yImage[1].getWidth() / 2),(yImage[1].getHeight()/2));	// ��]�̃Z�b�g

			if( rotatesize >= 360 )
			{
				rotatesize = 0;
			}
			AffineTransformOp atOp = new AffineTransformOp(at, null);

			BufferedImage[] revimg = new BufferedImage[yImage.length];
			for(int i=0;i<yImage.length;i++)
			{
				revimg[i] = atOp.filter(yImage[i], null);	// ���ۂɉ摜�ϊ����s���Ă�̂̓R�R
			}
			RotateYukkuri.setImages(revimg);
		}
		if( !nowLoading )
		{	
			if( !nowFading )
			{	// �܂����[�h�n�܂��ĂȂ���
				if( LoadExitedScene == �萔.SCENE_GAME01 )
				{
					iLoadertmp = ImageLoader.Get_Instance(owner);
					iLoadertmp.start();		// ���[�h�J�n
					otmp = new OtherLoader(owner);
					otmp.setLogicFlag(�萔.���[�f�B���O_���W�b�N1);		// ���݃��W�b�N�𕪂���K�v�͐����Ă��Ȃ����O�̂���
					otmp.start();
					
					nowLoading = true;
				}
				if ( LoadExitedScene == �萔.SCENE_LOAD )
				{
					iLoadertmp = ImageLoader.Get_Instance(owner);				
					iLoadertmp.start();		// ���[�h�J�n
					otmp = new OtherLoader(owner);
					otmp.setLogicFlag(�萔.���[�f�B���O_���W�b�N1);		// ���݃��W�b�N�𕪂���K�v�͐����Ă��Ȃ����O�̂���
					otmp.start();
					nowLoading = true;
				}
			}
		}
		else
		{ 	// ���łɃ��[�f�B���O�X���b�h�������Ă邨
			boolean AllThreadisExit = (!iLoadertmp.isAlive())&&(!otmp.isAlive());
			
			if( AllThreadisExit )
			{	//�X���b�h�������I��������
				owner.setImageLoader( iLoadertmp );
				nowLoading = false;
				nowFading = true;
				this.FadeOutToScene( LoadExitedScene );
			}
		}
	}
		
	/**
	 * ���[�f�B���O�����I�����΂��V�[���i���o���Z�b�g����
	 * @param sceneno
	 */
	public void setNextSceneNo( int sceneno )
	{
		LoadExitedScene = sceneno;
	}
	
	/**
	 * �I������
	 */
	@Override
	public void DestractFadeOut()
	{
		super.DestractFadeOut();
		nowFading = false;
		LoadExitedScene = -1;
		
	}
}
