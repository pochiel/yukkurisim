package yukkurisim;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.*;
import com.golden.gamedev.*;
import com.golden.gamedev.object.*;
import com.golden.gamedev.Game;

import yukkurisim.yukkuri_base;
import yukkurisim.yukkurisim_main;

public class ADV_SpriteGroup_base extends SpriteGroup implements Serializable {
	private yukkurisim_main owner;	//オーナー
	
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
	 * オブジェクト郡をクリックした際、クリックされているオブジェクトに関してのみ
	 * クリック処理を実行する。
	 * ついでにオンマウス処理も実施する。
	 */
	public void doClickEvent()
	{
		this.sort(this.getComparator());		// ここでソート
		Sprite[]	msprites = this.getSprites();
		int			msize = this.getSize();
		Object_base temp;
		
		// オンマウス処理
		for(int i=(msize-1);i>=0;i--){
			temp = (Object_base)msprites[i];
			
			if((temp.isActive())&&(temp.isMouseRide())){				
				temp.doOnMouseEvent();
				break;
			}
		}

		// クリック処理
		for(int i=(msize-1);i>=0;i--){
			temp = (Object_base)msprites[i];
			
			if((temp.isActive())&&(temp.isClicked())){				
				temp.doClickEvent();
				break;
			}
		}
	}	
}
