package yukkurisim;

import gamestatus.Const_Value;
import gamestatus.GameTimer;

import yukkurisim.yukkurisim_main;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.CollisionGroup;

public class Physical_Law_Facade  extends CollisionGroup{

	private yukkurisim_main owner;					//�I�[�i�[Game�C���X�^���X�ւ̎Q��
	private  Const_Value �萔 = new Const_Value();
	
	/************************ �p�[�g�Ǘ��֘A �������� *******************************/
	private int PartKbn;

	/**
	 * �p�[�g���ړ�����
	 *
	 */
	public void Change_Part()
	{
		/** �{��(���z)�p�[�g���{��(�s��)�p�[�g **/
		if( this.PartKbn==�萔.�p�[�g_�{��_���z�敪 )
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			this.PartKbn=�萔.�p�[�g_�{��_�s���敪;
		}
		/** �{��(�s��)�p�[�g���琬�p�[�g **/
		else if(this.PartKbn==�萔.�p�[�g_�{��_�s���敪)
		{
			/* �S�Ă̈ړ��\�I�u�W�F�N�g��ҋ@���ɕύX����*/
			Sprite[] tmp = MapObj.getSprites();
			for(int i = 0;i< tmp.length;i++)
			{
				if(tmp[i]!=null)
				{
					Object_base o = (Object_base)tmp[i];
					if(o.Get_Type()<�萔.TYPE_object_�L�����N�^�ƌ��z���ԍ��~���l)
					{	//����\�ȃI�u�W�F�N�g�ł���
						((yukkuri_base)o).setState(�萔.�ҋ@��);
					}
				}
			}
			Widget_Manager.Get_Instance(owner).ClsMessage();

			Widget_Manager.Get_Instance(owner).DrawMessage(GameTimer.Get_Instance(owner, �萔.�摜�ԍ�_�^�C�}).Get_Days()+"����...", this);

			this.PartKbn=�萔.�p�[�g_�琬�敪;
		}
		/** �琬�p�[�g���P���p�[�g **/
		else if(this.PartKbn==�萔.�p�[�g_�琬�敪)
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			//this.PartKbn=�萔.�p�[�g_�P���敪;		//���݂̂Ƃ���P���p�[�g�͖����̂ł�������
			this.PartKbn=�萔.�p�[�g_�{��_���z�敪;			
		}
		/** �P���p�[�g���{��p�[�g **/
		else if(this.PartKbn==�萔.�p�[�g_�P���敪)
		{
			Widget_Manager.Get_Instance(owner).ClsMessage();
			this.PartKbn=�萔.�p�[�g_�{��_���z�敪;
		}
	}
	
	/**
	 * ���ԋ敪�i��or��j��int�ŕԂ�.
	 * @return
	 */
	public int Get_Part()
	{
		return this.PartKbn;
	}


	/************************ �p�[�g�Ǘ��֘A �����܂� *******************************/
	
	// �����@���̕␳�ΏۂƂȂ�Ǘ��Ώۂ̓}�b�v�^�C���A�}�b�v�I�u�W�F�N�g
	// �����ăL�����N�^�[�I�u�W�F�N�g�̂R��
	private MapTileManager 		MapTile;
	private MapObject_Manager 		MapObj;
	private ItemManager			ItemMan;
	
	// �^�C���}�l�[�W���̃Q�b�^
	public MapTileManager Get_TileManager()
	{
		return MapTile;
	}
	
	// �I�u�W�F�N�g�}�l�[�W���̃Q�b�^
	public MapObject_Manager Get_ObjectManager()
	{
		return MapObj;
	}
	
	public ItemManager Get_ItemManager() {
		return ItemMan;
	}


	/* �B��̃R���X�g���N�^ */
	public Physical_Law_Facade(yukkurisim_main own)
	{
		owner = own;
		this.PartKbn=�萔.�p�[�g_�{��_���z�敪;		// �{��p�[�g����n�܂�

	}
	
	/**
	 * �����@���t�@�T�[�h�N���X�Ŏ󂯓n�����Ǘ����ׂ��}�l�[�W�����Z�b�g����
	 * @param t
	 * @param o
	 * @param i
	 */
	public void Set_Manager(MapTileManager t , MapObject_Manager o , ItemManager i)
	{
		this.MapObj = o;
		this.MapTile = t;
		this.ItemMan = i;
		this.setCollisionGroup(t.GetMapGroup(), o);
	}
	private static Physical_Law_Facade 	myself = null;

	/******* �C���X�^���X�𓾂�(�e�C���X�^���X�ƈ�������) ********/
	public static synchronized Physical_Law_Facade Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new Physical_Law_Facade(own);
		}

		return myself;
	}

	/* ���炩�̉^���𔺂��v�l�ɂ��āA�ӎ��I��͕̂����@���ɂ���������
	 * �^��������̂ł͂Ȃ��A�����܂Ŋ�]�Ƃ��Ă����������^�����s�������B
	 * �Ƃ����~���̉��R��ׂ��^�����s���A���ꂪ�����@���ɂ���ĕ␳�����B
	 * ���������āA��p���E����p���E�����@�����ꂼ��̊T�O���Ɨ��ł��邱�Ƃ�
	 * �����܂ł��Ȃ����A���ۂ��̂��̂ɂ��Č��y����΁A��p���̂��������͔\���I�ł���
	 * ���ʂ͎󓮓I�ł���Ƃ�����B
	 * ���������݌v�I�Ɍ����ƁA����p���ɍ�p���s�����߂ɒ��ڍ�p�����邱�Ƃ��ł����
	 * ����ɉz�������Ƃ͖������A�����@�����o�R��������𓾂Ȃ��B�@�Ƃ������Ƃł���B
	 * ����āA�^���\��object���^�����s�������̂� */
	public boolean MoveTo(
							Object_base obj,
							long elapsedTime,
							double xs,
							double ys,
							double speed)
	{
		boolean tmp;
		tmp = obj.moveTo(elapsedTime, xs, ys, speed);
		obj.Set_Cell_From_Real_Location();			// �ړ���̃Z�����X�V
				
		return tmp;
	}

	private Object_base MySprite1;
	private Object_base MySprite2;
	
	@Override
	public void collided(Sprite arg0, Sprite arg1) 
	{
		// �Ԃ������Ƃ��`��
		MySprite1 = (Object_base)arg0;
		MySprite2 = (Object_base)arg1;
	
		// sprite1���������object
		if( ( MySprite1.Get_Type()==�萔.TYPE_�������object ))
		{
			// �Y���Z���ɓ����Ă������ł����H
			if( !MapTile.Check_able_to_Entry(MySprite2.my_x, MySprite2.my_y, MySprite1.Get_Type()))
			{
				// ��������_���Ȃ�

				if(MapTile.Check_able_to_Entry(MySprite1.old_my_x, MySprite1.old_my_y, MySprite1.Get_Type()))
				{
					GetBack(MySprite1,MySprite2);
				}

			}
			else if(!MapObj.Check_able_to_Entry(MySprite2.my_x, MySprite2.my_y, MySprite1.Get_Type()))
			{
				if(MapTile.Check_able_to_Entry(MySprite1.old_my_x, MySprite1.old_my_y, MySprite1.Get_Type()))
				{
					GetBack(MySprite1,MySprite2);
				}
				
			}
			//MySprite1.Set_Cell_From_Real_Location();			// �ړ���̃Z�����X�V

		}
		// sprite2���������object
		if( ( MySprite2.Get_Type()==�萔.TYPE_�������object )&&(!(MySprite2.recovering)))
		{
			// �Y���Z���ɓ����Ă������ł����H
			if( !MapTile.Check_able_to_Entry(MySprite1.my_x, MySprite1.my_y, MySprite2.Get_Type()))
			{
				// ��������_���Ȃ�
				if(MapTile.Check_able_to_Entry(MySprite2.old_my_x, MySprite2.old_my_y, MySprite2.Get_Type()))
				{
					GetBack(MySprite2,MySprite1);
					// �߂낤�Ƃ��Ă����Z�����i���\�ł���Ȃ��
				   	// �߂�B�i�i���s�Z���֖߂낤�Ƃ��Ķ޸�޸�����ق���o�O�΍�)
					/*MySprite2.go2x=(int)MySprite2.Get_Pixel_x(MySprite2.old_my_x, MySprite2.old_my_y);
					MySprite2.go2y=(int)MySprite2.Get_Pixel_y(MySprite2.old_my_x, MySprite2.old_my_y);

					System.out.println("2,"+MySprite2.myId+","+MySprite2.my_x+","+MySprite2.my_y+","+MySprite2.old_my_x+","+MySprite2.old_my_y+","+MySprite2.go2x+","+MySprite2.go2y+",recovering=1");

					MySprite2.old_my_x = MySprite1.my_x;		// ������莩�̂��傫�����߁A��Q���̂��Ȃ��O�ŏՓ˔��肪����
					MySprite2.old_my_y = MySprite1.my_y;		// ���ׁ̈A�Փˎ���old_x�ޔ��͏�Q���̍��W�𒼂œ��͂��Ȃ���
																// ��Q�ɍĂі߂낤�Ƃ���̂��֎~���郋�[�`�����g�߂Ȃ��B
					((yukkuri_base)MySprite2).setState(�萔.�ړ���);
					MySprite2.recovering = true;*/
				}
			}
			else if(!MapObj.Check_able_to_Entry(MySprite1.my_x, MySprite1.my_y, MySprite2.Get_Type()))
			{
				// ��������_���Ȃ�
				if(MapTile.Check_able_to_Entry(MySprite2.old_my_x, MySprite2.old_my_y, MySprite2.Get_Type()))
				{
					GetBack(MySprite2,MySprite1);	//�߂�
				}
				
			}
		}

	}
	
	/** �������͈����Ԃ� 
	 *  ���������������ɂȂ�悤�Ɏ󂯓n���B
	 * **/
	public void GetBack(Object_base arg0, Object_base arg1)
	{
		arg0.go2x=arg0.Get_Pixel_x(arg0.old_my_x, arg0.old_my_y);
		arg0.go2y=arg0.Get_Pixel_y(arg0.old_my_x, arg0.old_my_y);

		//System.out.println("2,"+arg0.myId+","+arg0.my_x+","+arg0.my_y+","+arg0.old_my_x+","+arg0.old_my_y+","+arg0.go2x+","+arg0.go2y+",recovering=1");

		arg0.old_my_x = arg1.my_x;					// ������莩�̂��傫�����߁A��Q���̂��Ȃ��O�ŏՓ˔��肪����
		arg0.old_my_y = arg1.my_y;					// ���ׁ̈A�Փˎ���old_x�ޔ��͏�Q���̍��W�𒼂œ��͂��Ȃ���
													// ��Q�ɍĂі߂낤�Ƃ���̂��֎~���郋�[�`�����g�߂Ȃ��B
		((yukkuri_base)arg0).setState(�萔.�ړ���);
		arg0.recovering = true;
	}

}
