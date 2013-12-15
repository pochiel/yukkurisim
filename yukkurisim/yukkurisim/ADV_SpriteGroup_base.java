package yukkurisim;

import java.io.Serializable;
import com.golden.gamedev.object.*;
import yukkurisim.yukkurisim_main;

public class ADV_SpriteGroup_base extends SpriteGroup implements Serializable {
	private yukkurisim_main owner;	//�I�[�i�[
	
	public ADV_SpriteGroup_base(yukkurisim_main own,String n)
	{
		super(n);
		owner = own;
	}
	public void Initialize()
	{
		//yukkuri_base Acter[] = (yukkuri_base[])(this.getSprites());
		Object_base tmp;

		for( int i = 0 ; i < this.getSize();i++ )
		{
			tmp = (Object_base) this.getActiveSprite();
			//tmp = this.getActiveSprite();
			
			tmp.Initialize();
		}
	}

	/***
	 * �I�u�W�F�N�g�S���N���b�N�����ہA�N���b�N����Ă���I�u�W�F�N�g�Ɋւ��Ă̂�
	 * �N���b�N���������s����B
	 * ���łɃI���}�E�X���������{����B
	 */
	public void doClickEvent()
	{
		this.sort(this.getComparator());		// �����Ń\�[�g
		Sprite[]	msprites = this.getSprites();
		int			msize = this.getSize();
		Object_base temp;
		
		// �I���}�E�X����
		for(int i=(msize-1);i>=0;i--){
			temp = (Object_base)msprites[i];
			
			if((temp.isActive())&&(temp.isMouseRide())){				
				temp.doOnMouseEvent();
				break;
			}
		}

		// �N���b�N����
		for(int i=(msize-1);i>=0;i--){
			temp = (Object_base)msprites[i];
			
			if((temp.isActive())&&(temp.isClicked())){
				System.out.println("��������"+temp);
				temp.doClickEvent();
				break;
			}
		}
	}	
}
