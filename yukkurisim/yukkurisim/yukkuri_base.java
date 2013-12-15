package	yukkurisim;
//JFC
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import gamestatus.Const_Value;
import gamestatus.GameInfoManager;
import gamestatus.GameTimer;
import gamestatus.SaveData;
import java.util.Random;
import java.util.Vector;

/****************************************************
 * ������蓙�I�u�W�F�N�g�̃x�[�X�N���X�����ꒊ�ۉ�
 * @author �|�`�G��
 * @version 0.1
 */

public class yukkuri_base extends Object_base
{
	private int	y_birthday;		//�������̒a����(�����ڂɐ��܂ꂽ��)
	private int	y_hp;			//�������̃o�C�^���e�B�A�Ȃ��Ȃ�Ǝ���ł��܂��B
	private int	y_grow_lv;		//�������̐����i�K������킷�B(0�`2)
	private int	y_exp;			//�������̐����i�K��ω�������p�����[�^�B
	private int	y_hungry;		//�������̋󕠓x�B
	private int	y_sweety;		//��������H�ׂ����̊Â��B
	private int	y_happynes;		//������Ƒ�����A�s�҂���ƌ���A���x�ɔ���Ⴕ�A��p�x�EHP�ɔ�Ⴗ��B
	private int	y_dex;			//�K���x�ƌo���l�Ɛ����x�ɉe�������B
	private int	y_cooperative;	//�������A�_�Ƃ̐��ʂɉe������
	private int	y_hunting;		//��̔\�́A��̐��ʂɉe������
		
	private int	state;		//���������
	
	private int	y_id;		//
	private int	parent_id;	//
	private int[]	child_id;	//
	
	/**************�@�e���|�����n�@***************/
	private int	tmp_eat;	//�������̐H���v���O���X
	private int	debug_tmp;
	/**************�@�e���|�����n�@***************/

	BufferedImage[]	yukkuri_wait;			//�҂���Ԃ̉摜
	BufferedImage[]	yukkuri_eat;			//�H����Ԃ̉摜
	BufferedImage[]	yukkuri_dead_desease;	//�a����Ԃ̉摜
	BufferedImage[]	yukkuri_dead;			//���S��Ԃ̉摜

	BufferedImage[]	yukkuri_img;

	public SaveData getSaveData()
	{
		SaveData mysave = new SaveData();
		mysave.birthday = this.y_birthday;
		mysave.cooper = this.y_cooperative;
		mysave.dex = this.y_dex;
		mysave.exp = this.y_exp;
		mysave.growlv = this.y_grow_lv;
		mysave.happy = this.y_happynes;
		mysave.hp = this.y_hp;
		mysave.hungry = this.y_hungry;
		mysave.hunt = this.y_hunting;
		mysave.my_x = this.my_x;
		mysave.my_y = this.my_y;
		mysave.state = this.GetState();
		mysave.sweety = this.y_sweety;
		mysave.syuzoku = this.Get_Type();
		mysave.itemflag = false;
		
		return mysave;
	}
	
	public void setSaveData(SaveData mysave)
	{
		 this.y_birthday	=	mysave.birthday ;
		 this.y_cooperative	=	mysave.cooper ;
		 this.y_dex	=	mysave.dex ;
		 this.y_exp	=	mysave.exp ;
		 this.y_grow_lv	=	mysave.growlv ;
		 this.y_happynes	=	mysave.happy ;
		 this.y_hp	=	mysave.hp ;
		 this.y_hungry	=	mysave.hungry ;
		 this.y_hunting	=	mysave.hunt ;
		 this.location(mysave.my_x, mysave.my_y);
		 this.setState(mysave.state) ;
		 this.y_sweety	=	mysave.sweety ;
	}
	
	public yukkuri_base(
			yukkurisim_main own
			)

	{
		super( own );		//�e�N���X�̃R���X�g���N�^�R�[��
		//this.owner = own;		//�Ăяo�����N���X�ւ̃|�C���^
		debug_tmp = 0;		//���f�o�b�O
		initialize();		//������
	}
	
	/**
	 * �Z���ʒu����ύX���A�s����ϐ��Ɏ����W��������B
	 * location��Move_To�̈Ⴂ�� location���u�Ԉړ� Move_To���ړ��J�n �ł��肽���B
	 */
	@Override
	public void location( int x , int y )
	{
		super.location(x, y);
	}
	
	
	/***
	 * �N���b�N���̃C�x���g�L�q
	 */
	@Override
	public void doClickEvent()
	{
		// �X�e�[�^�X�E�C���h�E�Ώۓ��͂ő҂���Ԃ��H
		if( Widget_Manager.Get_Instance(owner).isStatusWait() )
		{
			// �����̃X�e�[�^�X�E�C���h�E��\��
			Widget_Manager.Get_Instance(owner).showStatusWidget(	this.Get_Type(),			// �푰
																	this.getHP(),				// �̗�
																	this.Get_Cooperative(),		// ������i�������j
																	this.Get_Hunting(),			// ������i���j
																	this.getSweety(),			// �Â�
																	this.getHungry(),			// ���Ȃ�
																	this.getBirthday());		// �a����
			Widget_Manager.Get_Instance(owner).setStatusWait(false);
		}
	}
	
	/**
	 * �琬�p�[�g�ɂāA��肪�w�肳�ꂽ���̓���p�^�[���B
	 *
	 */
	public void doKari(long elapsedTime)
	{
		if(this.state < �萔.���S )
		{
			// �󕠓x���Z����
			if( this.y_hungry >= 1)
			{
				this.y_hungry -= 1;
			}
			if( this.y_hungry <= 0 )
			{	//�󕠓x��0�ɂȂ����玀��
				this.setState(�萔.���S);
			}
			else	///////////// �ȉ��A���펞���� ///////////////
			{
				if( GameTimer.Get_Instance(owner, 0).Get_Times() == �萔.����̒���/2)
				{	// get_instance�̓�߂̈����̓_�~�[
					//�A�C�e���擾����
					KariAction.Get_Instance(owner).getItemChallenge(this);
					System.out.println("�^�C�}���쁨"+GameTimer.Get_Instance(owner, 0).Get_Times());
					this.setState(�萔.����ҋ@��);
				}
				else if( this.state == �萔.�_�ƌ�ҋ@�� )
				{
					//���̂Ƃ��듮���Ȃ�
				}
			}
			
		}
		// ���S���Ă��Ȃ���΂����܂ŁA�ȉ��͎��S�����Ƃ��̏���
		else
		{
			//�ŏI�t���[���Ȃ�΂����łƂ߂�B
			if( this.getFinishAnimationFrame() == this.getFrame() )
			{
				this.setAnimate(false);
				this.setActive(false);
			}
		}
		//UpdateImage();
		
	}
	
	/**
	 * �琬�p�[�g�ɂāA�_�Ƃ��w�肳�ꂽ���̓���p�^�[���B
	 *
	 */
	public void doFarm(long elapsedTime)
	{
		if(this.state < �萔.���S )
		{
			// �󕠓x���Z����
			if( this.y_hungry >= 1)
			{
				this.y_hungry -= 1;
			}
			if( this.y_hungry <= 0 )
			{	//�󕠓x��0�ɂȂ����玀��
				//this.setImages(yukkuri_dead);
				this.setAnimate(true); 					// �A�j���[�V��������
				this.setLoopAnim(false);					// �A�j���[�V���������[�v�����Ȃ�
				this.getAnimationTimer().setDelay(130);		// 13/1000�i8�R�}/sec) �ŕ\��
				this.setState(�萔.���S);
			}
			else	///////////// �ȉ��A���펞���� ///////////////
			{
				if(this.state == �萔.�ړ���)
				{
					//this.moveTo( elapsedTime , go2x, go2y, 0.05);
					PhLaw.MoveTo(this, elapsedTime , go2x, go2y, 0.05);		// �������f��
																			// �ړ��\�Ȃ�Γ���					
					if( (this.getX() == go2x)&&(this.getY() == go2y) )
					{
						debug_tmp = 2;	//���f�o�b�O
						//this.setImages(yukkuri_wait);
						this.setState(�萔.�_�ƒ�);
						if(this.recovering)
						{
							
							this.recovering = false;
						}
					}
				}
				else if( this.state == �萔.�ҋ@�� )
				{
					// �_�n���X�g�x�N�^���擾
					Vector<Object_base> farms = Physical_Law_Facade.Get_Instance(owner).Get_ObjectManager().getFarmVector();
			
					// �ΏۂƂȂ�_�n��������_���Ɍ���
					Random sai = new Random();
					int t = sai.nextInt(farms.size());
					Farm_Object targetfarm = (Farm_Object)farms.get(t);
					
					System.out.println("�������ԍ���"+this.myId+"  �ړI��"+targetfarm.my_x+","+targetfarm.my_y+"  ������"+farms.size());
					
					this.Move_To(targetfarm.my_x, targetfarm.my_y);
		        	this.setState(�萔.�ړ���);
				}
				else if( this.state == �萔.�_�ƒ� )
				{
					// ����̔����̎��ԁA�_�Ƃɏ]�������邱�ƂŔ_�Ƃ�B�������Ƃ݂Ȃ��B
					// ���Ȃ킿�A�_�n�ɓ��B�ł��Ȃ������������͂��������_�Ƃ�B�������Ƃ݂Ȃ��Ȃ��B
					
					if( GameTimer.Get_Instance(owner, 0).Get_Times() == �萔.����̒���/2)
					{	// get_instance�̓�߂̈����̓_�~�[
						//�A�C�e���擾����
						FarmAction.Get_Instance(owner).getItemChallenge(this);
						System.out.println("�^�C�}���쁨"+GameTimer.Get_Instance(owner, 0).Get_Times());
						this.setState(�萔.�_�ƌ�ҋ@��);
					}
				}
				else if( this.state == �萔.�_�ƌ�ҋ@�� )
				{
					//���̂Ƃ��듮���Ȃ�
				}
			}
		}
		// ���S���Ă��Ȃ���΂����܂ŁA�ȉ��͎��S�����Ƃ��̏���
		else
		{
			//�ŏI�t���[���Ȃ�΂����łƂ߂�B
			if( this.getFinishAnimationFrame() == this.getFrame() )
			{
				this.setAnimate(false);
				this.setActiveToFade(false);
			}
		}
		UpdateImage();
	}
	
	/**
	 * �琬�p�[�g�ɂāA�V��ł�����Ƃ���ꂽ���̓���p�^�[���B
	 *
	 */
	public void doPlay(long elapsedTime)
	{
		if ( this.state < �萔.���S )
		{
			// �󕠓x���Z����
			if( this.y_hungry >= 1)
			{
				this.y_hungry -= 1;
			}
			if( this.y_hungry <= 0 )
			{	//�󕠓x��0�ɂȂ����玀��
				//this.setImages(yukkuri_dead);
				this.setAnimate(true); 					// �A�j���[�V��������
				this.setLoopAnim(false);					// �A�j���[�V���������[�v�����Ȃ�
				this.getAnimationTimer().setDelay(130);		// 13/1000�i8�R�}/sec) �ŕ\��
				this.setState(�萔.���S);
			}
			else	///////////// �ȉ��A���펞���� ///////////////
			{
				////// ���Ȃ����ւ����� �������� ////////
				if( (this.y_hungry < 4000)&&(!(this.recovering)))	//�������A���J�o�����͂��͂��H�ׂɂ����Ȃ�
				{
					for(int i = -2;i<3;i++)
					{
						for(int j = -2;j<3;j++)
						{
							if
								(
									(this.my_x + i < 0)||(this.my_y + j < 0)||
									(this.my_x + i >= �萔.��ʉ��Z����)||
									(this.my_y + j >= �萔.��ʏc�Z����)
								)	//�T���͈͂���ʊO�ɂȂ�ꍇ�������Ȃ��B
							{
									//�������Ȃ�
							}
							else
							{
								if ( PhLaw.Get_ObjectManager().Get_obj_type(this.my_x + i, this.my_y + j) == �萔.TYPE_��object )
								{//�؂��������I
									if( this.Get_Range(
											this.Get_Pixel_x(this.my_x + i, this.my_y + j) + (�萔.�}�b�v�`�b�v����/2),
											this.Get_Pixel_y(this.my_x + i, this.my_y + j) + (�萔.�}�b�v�`�b�v�c��/2) )
										<= �萔.�}�b�v�`�b�v���� )		// �Ώۃ}�b�v�Z���̒��S�_���玩�g�̒��S�_�܂ł̋�����
																		// �}�b�v�`�b�v������菬�����B
									{//�؂̋߂��Ȃ̂ŁA�H�ׂ�	
										if (PhLaw.Get_ObjectManager().Get_obj_pointer(this.my_x + i, this.my_y + j) == null )
										{
											System.out.println("���f�o�b�O���");
											System.out.println("this.my_x=" + this.my_x );
											System.out.println("this.my_y=" + this.my_y );
											System.out.println("i=" + i );
											System.out.println("j=" + j );
										}
										this.y_hungry += ((Tree_Object)PhLaw.Get_ObjectManager().Get_obj_pointer(this.my_x + i, this.my_y + j)).Get_Food();
										System.out.println("���H������");
										//this.setImages(yukkuri_eat);
										this.setState(�萔.�H����);
										if( this.y_hungry>10000 )
										{
											this.y_hungry=10000;
										}
										break;
									}
									else
									{	//�߂Â��i��ŏ��������j
										this.Move_To(this.my_x + i+1, this.my_y+j+1);
										this.setState(�萔.�ړ���);
										break;
									}
									
								}
							}
						}
					}
				}
				
				if( this.state == �萔.�H����){
					this.tmp_eat--;
					if( this.tmp_eat == 0 )
					{
						this.tmp_eat = 100;
						debug_tmp = 1;		//���f�o�b�O
						//this.setImages(yukkuri_wait);
						this.setState(�萔.�ҋ@��);
						if(this.recovering)
						{
							//���������A���J�o�����̂���H�������邱�Ǝ��̂��������B
							//System.out.println("0,"+myId+","+my_x+","+my_y+","+old_my_x+","+old_my_y+","+go2x+","+go2y+",recovering=0");
							this.recovering = false;
						}

					}
				}
		
				////// ���Ȃ����ւ����� �����܂� ////////		
				if(this.state == �萔.�ړ���)
				{
					//this.moveTo( elapsedTime , go2x, go2y, 0.05);
					PhLaw.MoveTo(this, elapsedTime , go2x, go2y, 0.05);		// �������f��
																			// �ړ��\�Ȃ�Γ���					
					if( (this.getX() == go2x)&&(this.getY() == go2y) )
					{
						debug_tmp = 2;	//���f�o�b�O
						//this.setImages(yukkuri_wait);
						this.setState(�萔.�ҋ@��);
						if(this.recovering)
						{
							//System.out.println("5,"+myId+","+my_x+","+my_y+","+old_my_x+","+old_my_y+","+go2x+","+go2y+",recovering=0");
							this.recovering = false;
						}
					}
				}
				if( this.state == �萔.�ҋ@�� )
				{
					Random sai = new Random();
					
					if( sai.nextInt( 100 ) == 1)
					{
						this.Move_To( sai.nextInt( �萔.��ʉ��Z����) ,sai.nextInt( �萔.��ʏc�Z���� ));
			        	this.setState(�萔.�ړ���);
					}
				}
			}	//���펞���������܂�
		}		//���S���Ă��Ȃ���΁A�����܂ŁB
		else
		{
			//�ŏI�t���[���Ȃ�΂����łƂ߂�B
			if( this.getFinishAnimationFrame() == this.getFrame() )
			{
				this.setAnimate(false);
				this.setActiveToFade(false);
			}
		}
		UpdateImage();		
	}
	
	@Override
	public void update(long elapsedTime){
		
		super.update(elapsedTime);	
		if(GameTimer.Get_Instance(owner, �萔.�摜�ԍ�_�^�C�}).isMoving() )	// �^�C�}�����삵�Ă���ԁi�^�C�}���쎞��update�����̗L���͊eobject�Ŏw�肵�Ȃ��ƁAFade���������s����Ȃ��i�b�`���E
		{
			// this.Get_Type()�̖߂�l����100�������Ă���͔̂z��̓��ɍ��킹�邽�߁B �푰�萔��100����n�܂�B
			if( GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100) == �萔.�A�N�V����_���)
			{
				doKari(elapsedTime);
			}
			else if( GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100) == �萔.�A�N�V����_�_��)
			{
				doFarm(elapsedTime);
			}
			else if( GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100) == �萔.�A�N�V����_�V��)
			{
				doPlay(elapsedTime);
			}
		}
	}

	/**
	 * ���݂̏�Ԃ���œK�ȉ摜��I�сAsetimages����B
	 *
	 */
	public void UpdateImage()
	{
		BufferedImage[] tmp = null;
		if(GetState()==�萔.�ړ���)
		{
			// �ړI�n�����ɂ���Ƃ���
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕ���_���]);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕ���);
			}
		}
		else if(GetState()==�萔.�H����)
		{
			// �ړI�n�����ɂ���Ƃ���
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ސH�ׂ�_���]);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ސH�ׂ�);
			}
			
		}
		else if(GetState()==�萔.�ҋ@��)
		{
			// �ړI�n�����ɂ���Ƃ���
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�_���]);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�);
			}
			
		}
		else if(GetState()==�萔.�_�ƌ�ҋ@��)
		{
			// �ړI�n�����ɂ���Ƃ���
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�_���]);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�);
			}
			
		}
		else if(GetState()==�萔.�_�ƒ�)
		{
			// �ړI�n�����ɂ���Ƃ���
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�_���]);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�);
			}
			
		}
		else if(GetState()==�萔.���S)
		{
			// �ړI�n�����ɂ���Ƃ���
			if( (this.getX()-this.go2x)<0 )
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ގ��S_���]);
			}
			else
			{
				tmp = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ގ��S);
			}
		}
		
		if(this.getImages()!=tmp)
		{
			this.setImages(tmp);
		}
	}
	
	public void setState(int ToState)
	{
		this.state = ToState;
	}

	@Override
	public void render(Graphics2D g)
	{
		if(Physical_Law_Facade.Get_Instance(owner).Get_Part()==�萔.�p�[�g_�琬�敪)
		{	// �琬�敪�̎��݂̂�������\��
			if(GameInfoManager.Get_Instance().getPlayPartState(this.Get_Type()-100)!=�萔.�A�N�V����_���)
			{	// ���ɍs���푰�����͕\�����Ȃ�
				super.render(g);
			}
		}
	}
	
	/*************************
	 * �Z���|�W�V����( my_x�� )�������W�ɕϊ����Ago2x go2y�Ɋi�[���܂�.
	 *
	 */
	public void Move_To( int x , int y )
	{
		this.go2x = this.Get_Pixel_x(x,y) + (�萔.�}�b�v�`�b�v���� - this.width)/2;
		this.go2y = this.Get_Pixel_y(x,y) + (�萔.�}�b�v�`�b�v�c�� - this.height)/2;

	}

	/**
	 * ��������Ԃ�
	 * @return
	 */
	public int Get_Cooperative()
	{
		return this.y_cooperative;
	}
	
	/**
	 * ��̔\�͂�Ԃ��B
	 * @return
	 */
	public int Get_Hunting()
	{
		return this.y_hunting;
	}
	
	/**************************************************************************
	 * ���������\�b�h�A�ʏ�I�u�W�F�N�g�̏������͂������I�[�o���C�h����B
	 *
	 */
	public void initialize()
	{
		//yukkuri_img = this.owner.getImages("image/reimuwalk.gif",12,1);
	    //yukkuri_wait = this.owner.getImages("image/reimuwait.gif",6,1);
	    //yukkuri_eat = this.owner.getImages("image/reimueat.gif",9,1 );
	    //yukkuri_dead_desease = this.owner.getImages("image/reumudesease.gif",1,1 );
	    //yukkuri_dead = this.owner.getImages("image/reimudead.gif",8,1 );
		yukkuri_img = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕ���);
	    yukkuri_wait = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�);
	    yukkuri_eat = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ސH�ׂ�);
	    yukkuri_dead_desease = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕa��);
	    yukkuri_dead = ImageLoader.Get_Instance(owner).getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ގ��S);
       	
	    y_birthday		= 0;
		y_hp			= 10;
		y_grow_lv		= 0;
		y_exp			= 0;
    	y_hungry		= �萔.����̒���*3;	// �󕠓x�͍ŏ�100
		y_sweety		= 10;
		y_happynes		= 0;
		y_dex			= 0;
		y_cooperative	= 0;		// ������
		y_hunting		= 0;		// ��̔\��
		
		
		//location( 10 , 10 , 10);

		this.setImages(yukkuri_wait);
		this.setAnimate(true); 				// �A�j���[�V��������
		this.setLoopAnim(true);				// �A�j���[�V���������[�v������
		
    	this.go2x = 0;
    	this.go2y = 0;
    	
    	tmp_eat = 100;
    	
    	
    	�萔 = new Const_Value();
    	this.state = �萔.�ҋ@��;
    	
    	PhLaw.Get_ObjectManager().add(this, �萔.TYPE_�������object);

	}

	@Override
	public int Get_Type() {
		return �萔.TYPE_�������object;
	}

	public int GetState()
	{
		return this.state;
	}

	public int getHungry() {
		return y_hungry;
	}

	public void setHungry(int h) {
		if(h<=0)
		{
			this.setState(�萔.���S);
			y_hungry=0;
		}
		else
		{
			y_hungry=h;			
		}
	}

	public int getSweety() {
		return y_sweety;
	}
	
	public void setSweety(int s){
		if( s<0 )
		{
			y_sweety = 0;
		}
		else
		{
			y_sweety = s;
		}
	}

	public int getHP() {
		return y_hp;
	}
	
	public void setHP(int h)
	{
		if(h<=0)
		{
			this.setState(�萔.���S);
			y_hp = 0;
		}
		else
		{
			y_hp = h;
		}
	}

	public int getBirthday() {
		return y_birthday;
	}

	public void setBirthday(int y_birthday) {
		this.y_birthday = y_birthday;
	}

	@Override
	public boolean isCharactor()
	{
		return true;	// ����̓L�����N�^�[�ł���
	}

	@Override
	public void doOnMouseEvent() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

}