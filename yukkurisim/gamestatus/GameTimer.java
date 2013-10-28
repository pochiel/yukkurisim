package gamestatus;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import yukkurisim.GameSaveLoader;
import yukkurisim.ImageLoader;
import yukkurisim.Object_base;
import yukkurisim.Other_Object;
import yukkurisim.yukkurisim_main;

import com.golden.gamedev.util.ImageUtil;

public class GameTimer extends Object_base{
	//private BufferedImage[] BackUpScrollingImage;
	private Object_base str_circle;
	/**
	 * ��]����Q�[���^�C�}�Ή�render
	 */
	public void render(Graphics2D g)
	{
		BufferedImage[] tempBuf = str_circle.getImages();
		tempBuf[0] = ImageUtil.rotate(ImageLoader.Get_Instance(this.owner).getBufferedImage(�萔.�摜�ԍ�_�^�C�}_������)[0],(int)(((float) this.Times / �萔.����̒���)*360));
		str_circle.setImages(tempBuf);
		str_circle.render(g);
		super.render(g);
	}
	
	public GameTimer(yukkurisim_main own) {
		super(own);
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
	}
	
	public GameTimer(yukkurisim_main own , BufferedImage[] arg0, double arg1, double arg2) {
		super(own,arg0,arg1,arg2);
		str_circle = new Other_Object(own,ImageLoader.Get_Instance(own).getBufferedImage(�萔.�摜�ԍ�_�^�C�}_������).clone());
		str_circle.setLocation(3, 3);
		str_circle.Set_Scrollable(false);
	}
	
	public static GameTimer myself;
	/**
	 *  �C���X�^���X�𓾂�(�e�C���X�^���X�ƈ�������)
	 *  �������̓C�j�V�����C�Y���̂ݎ擾����΂悢�B
	 *  */
	public static synchronized GameTimer Get_Instance( yukkurisim_main own,int imageNumber )
	{
		if( myself == null)
		{
			myself = new GameTimer(own,ImageLoader.Get_Instance(own).getBufferedImage(imageNumber),0,0);
		}

		return myself;
	}

	private int Times;
	private int Days;
	private boolean isMoving;
	private boolean TimeKbnisChanged;
	private  Const_Value �萔 = new Const_Value();
		
	public void Initialize()
	{
		this.Reset_Timer();
		this.Days=1;
		this.TimeKbnisChanged=true;
	}
	
	public void Time_Inc()
	{
		// �^�C�}�͓����Ă���H
		if( this.isMoving() )
		{
			// �琬�p�[�g�Ȃ玞�v�𓮂���
			if( PhLaw.Get_Part()==�萔.�p�[�g_�琬�敪 )
			{
				this.Times++;
			}
			// �琬�p�[�g�̏I������
			if( (PhLaw.Get_Part()==�萔.�p�[�g_�琬�敪)&&(this.Times > �萔.����̒���) )
			{
				// �琬�p�[�g�̂݁A�����ŏI�����肪�s����
				System.out.println("��"+Days+"���ڂ��I���I�I");
				this.Days++;
				
				/******* �琬�p�[�g�I�����Ɏ����ŃZ�[�u���s�� ********/
				GameSaveLoader iloader = new GameSaveLoader();
				iloader.SaveState(owner);
				/******* �Z�[�u�I�� *******/
				PhLaw.Change_Part();
			}
		}
	}
	
	
	public boolean Get_Time_kbn_Changed()
	{
		if(this.TimeKbnisChanged)
		{
			this.TimeKbnisChanged=false;
			return true;
		}
		return false;
	}
	
	/**
	 * �^�C�}�����삵�Ă��邩��boolean�ŕԂ�.
	 *
	 */
	public boolean isMoving()
	{
		return this.isMoving;
	}
	
	public void Reset_Timer()
	{
		this.Times=0;
	}
	
	public void Start_Timer()
	{
		this.isMoving=true;
	}
	
	public void Stop_Timer()
	{
		this.isMoving=false;
	}
	
	/**
	 * �^�C�}�̏�Ԃ�C�ӂ�boolean�lt�Ƃ��ăZ�b�g���܂�
	 * @param t
	 */
	public void Set_Timer_State(boolean t)
	{
		this.isMoving=t;
	}
	public int Get_Times()
	{
		return this.Times;
	}
	
	public int Get_Days()
	{
		return this.Days;
	}

	public int Get_Type() {
		return �萔.TYPE_���g�p;
	}

	public void doClickEvent() {
		// �g�p���Ȃ�
		
	}
	
	public boolean isCharactor()
	{
		return false;
	}

	@Override
	public void doOnMouseEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
}
