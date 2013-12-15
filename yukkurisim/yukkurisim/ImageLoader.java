package yukkurisim;
import gamestatus.Const_Value;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * �摜����x�ɓǂݍ��݁A�ێ����܂��B
 * �Ώۂ̉摜�ւ̃Q�b�^��񋟂��܂��B
 * @author pochiel
 *
 */
public class ImageLoader extends Thread {
	private volatile HashMap<Integer,BufferedImage[]> imagemap = new HashMap<Integer,BufferedImage[]>();
	private yukkurisim_main owner;					//�I�[�i�[Game�C���X�^���X�ւ̎Q��
	private  Const_Value �萔 = new Const_Value();
	private volatile static ImageLoader myself;
	
	/**
	 * �R���X�g���N�^
	 * @param own
	 */
	public ImageLoader(yukkurisim_main own)
	{
		owner = own;
		//LoadImage();	// �摜�̃��[�h
	}
	
	@Override
	public void run() {
		LoadImage();	// �摜�̃��[�h�J�n
	}
	
	/******* �C���X�^���X�𓾂�(�e�C���X�^���X�ƈ�������) ********/
	public static synchronized ImageLoader Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new ImageLoader(own);
		}

		return myself;
	}

	
	public synchronized void LoadImage()
	{
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕ���,owner.getImages("image/reimuwalk.gif",12,1));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�,owner.getImages("image/reimuwait.gif",6,1));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ސH�ׂ�,owner.getImages("image/reimueat.gif",9,1 ));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕa��,owner.getImages("image/reumudesease.gif",1,1 ));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ގ��S,owner.getImages("image/reimudead.gif",8,1 ));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕ���_���],reversImage(getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕ���)));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�_���],reversImage(getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޑ҂�)));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ސH�ׂ�_���],reversImage(getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ސH�ׂ�)));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕa��_���],reversImage(getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ޕa��)));
		setBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ގ��S_���],reversImage(getBufferedImage(�萔.�摜�ԍ�_�������ꂢ�ގ��S)));

		setBufferedImage(�萔.�摜�ԍ�_�J�[�\��,owner.getImages("image/cursor.gif", 4, 1));
		setBufferedImage(�萔.�摜�ԍ�_�^�C��0,owner.getImages("image/mapchip/paneldefault.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_��1,owner.getImages("image/mapchip/home1.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_��2,owner.getImages("image/mapchip/home2.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_��3,owner.getImages("image/mapchip/home3.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_��4,owner.getImages("image/mapchip/home4.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_��5,owner.getImages("image/mapchip/home5.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_��6,owner.getImages("image/mapchip/home6.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_����1,owner.getImages("image/mapchip/sougen1.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_����2,owner.getImages("image/mapchip/sougen2.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_��1,owner.getImages("image/mapchip/river1.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_��2,owner.getImages("image/mapchip/river2.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�G���g�����X,owner.getImages("image/mapchip/enterance.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_��,owner.getImages("image/mapchip/wood1.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�_�n,owner.getImages("image/mapchip/farm.gif",1,1));
		
		//************************ Widget�n�������� ***********************************//
		setBufferedImage(�萔.�摜�ԍ�_�`�F�b�N�{�b�N�X�I��,owner.getImages("image/widget/checkT.GIF", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_�`�F�b�N�{�b�N�X�I�t,owner.getImages("image/widget/checkF.GIF", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_�_�C�A���O�E�B���h�E,owner.getImages("image/widget/dialogwindow.GIF", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_���b�Z�[�W�E�B���h�E,owner.getImages("image/widget/MessageWindow.gif", 1, 1));
		setBufferedImage(�萔.�摜�ԍ�_�r���h�E�C���h�E,owner.getImages("image/widget/window_build.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�^�C�},owner.getImages("image/widget/timer.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�^�C�}_������,owner.getImages("image/widget/timer_circle.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_���j��,owner.getImages("image/widget/menu.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�`�F�b�N�{�^��,owner.getImages("image/widget/check_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�V���b�v�{�^��,owner.getImages("image/widget/shop_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�A�C�e���{�^��,owner.getImages("image/widget/item_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�g���b�v�{�^��,owner.getImages("image/widget/trap_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�r���h�{�^��,owner.getImages("image/widget/build_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�N���b�V���{�^��,owner.getImages("image/widget/crash_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�f�[�^�{�^��,owner.getImages("image/widget/data_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�N�C�b�N�{�^��,owner.getImages("image/widget/quick_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�p�j�b�V���{�^��,owner.getImages("image/widget/panish_b.gif",1,1));
		setBufferedImage(�萔.�摜�ԍ�_���u�{�^��,owner.getImages("image/widget/love_b.gif",1,1));		
		setBufferedImage(�萔.�摜�ԍ�_�؃{�^��,owner.getImages("image/widget/window_build_B_01.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�ƃ{�^��,owner.getImages("image/widget/window_build_B_02.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�_�n�{�^��,owner.getImages("image/widget/window_build_B_03.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_����{�^��,owner.getImages("image/widget/owaru.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�P���{�^��,owner.getImages("image/widget/tekkyo.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�A�N�V�����E�C���h�E,owner.getImages("image/widget/window_action.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�X�e�[�^�X�E�C���h�E,owner.getImages("image/widget/window_status.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�A�C�e���E�C���h�E,owner.getImages("image/widget/window_ItemUse.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�_�C�A���OOK,owner.getImages("image/widget/DialogOK.GIF",1,1));
		setBufferedImage(�萔.�摜�ԍ�_�A�C�e���n�e�i,owner.getImages("item/hatena.gif",1,1));

	}
	
	private synchronized void setBufferedImage(int i,BufferedImage[] image)
	{
		if(imagemap.get(i)!=null){
			// �������łɂȂ񂩓����Ă�I
			System.out.println("error key��"+i);
			throw new IllegalArgumentException
            ("�����̃L�[�l�ŃC���[�W�}�b�v���㏑�����悤�Ƃ��܂����B");
		}
		
		imagemap.put(i, image);
		
	}
	
	public synchronized BufferedImage[] getBufferedImage(int i)
	{
		//System.out.println("getBufferedImage("+i+")��"+(imagemap.get(i)==null?"null":imagemap.get(i)));
		if(imagemap.get(i)==null)
		{
			throw new IllegalArgumentException
			("���݂��Ȃ��摜�����[�h���悤�Ƃ��܂����B");
		}
		return imagemap.get(i);
	}
	
	/**
	 * �摜�𔽓]����BufferedImage[]���쐬���܂��B
	 * @param img
	 * @return
	 */
	private synchronized BufferedImage[] reversImage(BufferedImage [] img)
	{
		AffineTransform at = AffineTransform.getScaleInstance(-1.0, 1.0);
		at.translate(-1*(img[0].getWidth()), 0);
		AffineTransformOp atOp = new AffineTransformOp(at, null);

		BufferedImage[] revimg = new BufferedImage[img.length];
		for(int i=0;i<img.length;i++)
		{
			revimg[i] = atOp.filter(img[i], null);
		}
		
		return revimg;
	}

	public HashMap getmap()
	{
		return imagemap;
	}
	
}
