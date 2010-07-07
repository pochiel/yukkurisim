package yukkurisim;

import java.io.*;
import java.util.Vector;
import gamestatus.Const_Value;
import gamestatus.SaveData;
import com.golden.gamedev.object.*;

public class GameSaveLoader {
	private Const_Value 定数 = new Const_Value();
	
    private FileOutputStream outFile;
    private ObjectOutputStream outObject;
    private Vector<SaveData> mysavedata = new Vector<SaveData>();

    /** 
     * セーブデータインスタンスの作成
     *
     */
    public void makeSaveData(yukkurisim_main owner)
    {
    	/** オンマップオブジェクトのパッキング **/
    	{
	    	MapObject_Manager mapman = Physical_Law_Facade.Get_Instance(owner).Get_ObjectManager();
			Sprite[] tmpsprites = mapman.getSprites();
			int size = mapman.getSize();
			
			for(int i=0;i<size;i++)
			{
				Object_base tmp = (Object_base)tmpsprites[i];
				if(tmp.isActive())
				{
					if(tmp.Get_Type()==定数.TYPE_ゆっくりobject)
					{
						mysavedata.add(((yukkuri_base)tmp).getSaveData());
					}
					else if(tmp.Get_Type()==定数.TYPE_木object)
					{
						mysavedata.add(((Tree_Object)tmp).getSaveData());
					}
					else if(tmp.Get_Type()==定数.TYPE_家object)
					{
						if(((House_Object)tmp).isParent)
						{
							mysavedata.add(((House_Object)tmp).getSaveData());
							// 親オブジェクトのみセーブする
						}
					}
					tmp = (Object_base) mapman.getActiveSprite();
				}
			}
    	}
    	/** アイテムデータのパッキング **/
    	{
    		ItemManager itemman = ItemManager.Get_Instance(owner);
    		for(int i =0;i<定数.アイテム最大番号;i++)
    		{
    			SaveData[] tmp = itemman.getSaveData();
    			mysavedata.add(tmp[i]);
    		}
    	}    	
    }
    
    /** コンストラクタ **/
    public GameSaveLoader()
	{
	}
		
	public void SaveState(yukkurisim_main owner)
	{
		try
		{
			makeSaveData(owner);	// セーブデータインスタンスの作成
			outFile = new FileOutputStream("iurtilt.dat");	// 妹聞いて出てきた単語を元にファイル名決定
			outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(mysavedata);
			outObject.reset();
			outObject.close();
			outFile.close();
		}
		catch (IOException e) {
			// 例外処理は書かない fuck you!
			System.out.println("GameSaveLoader.exceptionキタコレ");
			System.out.println("例外→"+e);
	    }
		
	}
	public void LoadState(yukkurisim_main owner)
	{
		try
		{
			FileInputStream inFile = new FileInputStream("iurtilt.dat");
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			
			mysavedata = (Vector<SaveData>) inObject.readObject();
			
			inFile.close();
			inObject.close();
		}
		catch(Exception e)
		{
			
		}
		
		// ************* ベクタの中身を再生する ***************** //
		int vsize = mysavedata.size();
		MapObject_Manager mapman = Physical_Law_Facade.Get_Instance(owner).Get_ObjectManager();

		for(int i=0;i<vsize;i++)
		{
			SaveData tmp = mysavedata.get(i);
			if(tmp.itemflag)
			{
				// アイテムに関するデータである。
				ItemManager.Get_Instance(owner).setSaveData(tmp);
			}
			else
			{
				if(tmp.syuzoku==定数.TYPE_ゆっくりobject)
				{
					yukkuri_base	yukkuri_tmp = new yukkuri_base( owner );
					yukkuri_tmp.setSaveData(tmp);
					mapman.add( yukkuri_tmp,yukkuri_tmp.Get_Type() );
				}
				else if(tmp.syuzoku==定数.TYPE_木object)
				{
					System.out.println("木");
					Tree_Object t = new Tree_Object(owner,tmp.my_x,tmp.my_y);
					t.setSaveData(tmp);
					mapman.add(t,t.Get_Type());
				}
				else if(tmp.syuzoku==定数.TYPE_家object)
				{
					System.out.println("家");
					House_Object t = new House_Object(owner,tmp.my_x,tmp.my_y);
					t.setSaveData(tmp);
					mapman.add(t,t.Get_Type());
				}
			}
		}
	}
}
