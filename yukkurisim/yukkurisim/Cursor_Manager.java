package yukkurisim;

import java.awt.Graphics2D;
import gamestatus.Const_Value;

/**
 * �J�[�\���̕\���ƁA�X�N���[���֘A�̏����Ǘ�����B
 * @author pochiel
 *
 */
public class Cursor_Manager extends ADV_SpriteGroup_base {

	private int RelPosX;		// �X�N���[���ŕ\�������ʒu�̑��΍��W
	private int RelPosY;		// �}�E�X�ő��₷
    private Mapchip_base	myCursor = null;	// �J�[�\��
    private Widget_Base	myCustomer = null;	// �J�[�\���̖߂��
    private int			myCursor_s;			// �J�[�\�������敪
    private int			CursorCount = 0;	// �J�[�\���L�����܂ł̎���
    private int 			CursorWidth;		// �J�[�\�����i�Ɠ��̃}���`�}�b�v�I�u�W�F�N�g�ݒu�p�j
    private int			CursorHeight;		// �J�[�\�������i�Ɠ��̃}���`�}�b�v�I�u�W�F�N�g�ݒu�p�j

    protected yukkurisim_main owner;
	protected  Const_Value �萔 = new Const_Value();
	
	private static Cursor_Manager	myself = null;
	public Cursor_Manager(yukkurisim_main own,String s) {
		// TODO �����������ꂽ�R���X�g���N�^�[�E�X�^�u
		super(own,"CURSOR");
		owner = own;
	}
	/******* �C���X�^���X�𓾂�(�e�C���X�^���X�ƈ�������) ********/
	public static synchronized Cursor_Manager Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new Cursor_Manager(own,"CURSOR");
		}

		return myself;
	}

	public void Check_Cursor_Position()
	{
		int mx = owner.getMouseX();
		int my = owner.getMouseY();
		
		if(mx<=�萔.�X�N���[���V��)					// ��ʍ��[�Ł��X�N���[��
		{
			this.RelPosX+=2;
			if( this.RelPosX > �萔.�X�N���[���ő�͈�X )
			{
				this.RelPosX = �萔.�X�N���[���ő�͈�X;
			}
		}
		else if(mx>=�萔.��ʕ�-�萔.�X�N���[���V��)	// ��ʉE�[�Ł��X�N���[��
		{
			this.RelPosX-=2;
			if( this.RelPosX < �萔.�X�N���[���ŏ��͈�X )
			{
				this.RelPosX = �萔.�X�N���[���ŏ��͈�X;
			}
		}
		

		if(my<=�萔.�X�N���[���V��)					// ��ʏ�[�Ł��X�N���[��
		{
			this.RelPosY+=2;
			if( this.RelPosY > �萔.�X�N���[���ő�͈�Y )
			{
				this.RelPosY = �萔.�X�N���[���ő�͈�Y;
			}

		}
		else if(my>=�萔.��ʍ���-�萔.�X�N���[���V��)	// ��ʉ��[�Ł��X�N���[��
		{
			this.RelPosY-=2;
			if( this.RelPosY < �萔.�X�N���[���ŏ��͈�Y )
			{
				this.RelPosY = �萔.�X�N���[���ŏ��͈�Y;
			}
		}
	}
	
	public int Get_RelX()
	{
		return this.RelPosX;
	}
	
	public int Get_RelY()
	{
		return this.RelPosY;
	}
	/**
	 * �J�[�\���̕\����\����ύX���A�Ăяo�����C���X�^���X��Comeback_From_Cursor()��
	 * �R�[������B
	 * 
	 * @param customer ���s���̃C���X�^���X�ւ̎Q��
	 * @param t �J�[�\���̕\����\��
	 * @param s �����敪
	 */
	public void SetCursorActivate(Widget_Base customer,boolean t, int s,int width , int height)
	{
		CheckCursorInstanceisNotNull();
		
		if (customer==null)
		{
			// ���s�������݂��Ȃ��i�J�[�\���̕\�������ύX����j
			myCursor.setActive(t);
		}
		else
		{
			myCustomer = customer;
			myCursor_s = s;
			myCursor.setActive(t);
			CursorWidth = width;
			CursorHeight = height;			
		}
		CursorCount=20;
	}
	
    @Override
	public void render(Graphics2D g) {
    	CheckCursorInstanceisNotNull();
    	
    	double dmy_x;
    	double dmy_y;
    	double bkmy_x=myCursor.my_x;
    	double bkmy_y=myCursor.my_y;
    	
    	if( myCursor.isActive() )
    	{
    		for(int j=CursorHeight-1;j>=0;j--)
			{
				dmy_x=bkmy_x+(0.5*j);
				dmy_y=bkmy_y-j;
		    	if(bkmy_y%2!=0)
				{
					dmy_x+=0.5;		// y�ʒu�ɂ�鉡�ʒu�␳�␳
				}
				
		    	for(int i=0;i<CursorWidth;i++)
		    	{	//����    		
					myCursor.location((int)(dmy_x), (int)dmy_y);
					myCursor.render(g);
					dmy_x+=0.5;
					dmy_y++;
		    	}			
			}
    	}
    }
    
	@Override
	public void update(long elapsedTime)
	{
		CheckCursorInstanceisNotNull();

		if(myCursor.isActive())
		{	// �J�[�\�����A�N�e�B�u�Ȃ��
			myCursor.setX(owner.getMouseX() - this.Get_RelX());			//���W���}�E�X�ʒu�ɕύX
			myCursor.setY(owner.getMouseY() - this.Get_RelY());
			myCursor.Set_Cell_From_Real_Location();		//�����W����Z�����W���X�V
			myCursor.setX(myCursor.Get_Pixel_x(myCursor.my_x, myCursor.my_y));
			myCursor.setY(myCursor.Get_Pixel_y(myCursor.my_x, myCursor.my_y));
			
			if( myCursor.my_x < 0 )
			{
				myCursor.my_x=0;
			}
			if( myCursor.my_y < 0 )
			{
				myCursor.my_y=0;
			}
			if( myCursor.my_x > �萔.��ʉ��Z���� )
			{
				myCursor.my_x=�萔.��ʉ��Z����;
			}
			if( myCursor.my_y > �萔.��ʏc�Z���� )
			{
				myCursor.my_y=�萔.��ʏc�Z����;
			}
	
			myCursor.update(elapsedTime);				//update
		}
		
		if(CursorCount!=0)
		{
			CursorCount--;
		}
		else
		{
			
			if(myCursor.isActive())
			{	
				if(myCursor.isClicked())
				{
					myCustomer.Comeback_From_Cursor(myCursor.my_x , myCursor.my_y , myCursor_s);
					SetCursorActivate(null,false,0,0,0);		// �J�[�\������
				}				
			}
		}

		this.Check_Cursor_Position();
	}
	
	private void CheckCursorInstanceisNotNull()
	{
		/*
		 * �i�L�ցM�j���炷��
		 * �@.�J�[�\���}�l�[�W���̃R���X�g���N�^�ŃJ�[�\�����̂�new����
		 * �A.Object_base�̃R���X�g���N�^�ŃX�N���[�������p��Cursorman��GetInstance���R�[��
		 * �B.Cursorman��GetInstance�ŃJ�[�\���}�l�[�W���̃R���X�g���N�^���R�[��
		 * �C.�@�ɖ߂�
		 * �@��
		 * StackOverflowError�_(^o^)�^�l���I���^
		 * 
		 * ���傤���Ȃ��̂ŃJ�[�\����\���������ȉӏ��̓��ɂ��̊֐����R�\�[���Ǝd����
		 * �Q�F�b�I�����˂��I�����͏��ł����[���I
		 */
		if( myCursor==null )
		{
			System.out.println("�_(^o^)�^�l���I���^");
			myCursor = new Mapchip_base
			(
					owner , 
					owner.getImages("image/cursor.gif", 4, 1),
					//ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�J�[�\��),		�J�[�\���͊eObjectBase�ŃC�j�V�����C�Y����邽�߁AImageLoader���o�R�ł��Ȃ��A�}�W�E���R�\�[�X�ɂȂ��Ă��܂���
					0,
					0,
					0
			);
			myCursor.setActive(false);		// ������\��
			myCursor.Set_Scrollable(true);	// �X�N���[����
			myCursor.setAnimate(true);		// �A�j���[�V������
			myCursor.setLoopAnim(true);		// �A�j���[�V�����̓��[�v����
			myCursor.set_debug(100);				// �f�o�b�O
			this.add(myCursor);
		}
	}
	
}
