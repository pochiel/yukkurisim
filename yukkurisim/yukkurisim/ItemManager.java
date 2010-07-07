package yukkurisim;

import gamestatus.Const_Value;
import gamestatus.SaveData;
import yukkurisim.yukkurisim_main;
import java.io.*;
/**
 * アイテムそのものを管理するクラス。
 * （アイテム関連のインターフェースとしては用いない）
 * @author pochiel
 *
 */
public class ItemManager implements Serializable {
	private int[] ItemCount;			// index番のアイテムを何個所持しているか
	private boolean[] ItemKnowledge;		// index番のアイテムを既に知っているか
	
	/**
	 * アイテム番号のアイテムが既知かを返す。
	 * @param i
	 * @return
	 */
	public boolean isKnown(int i)
	{
		return this.ItemKnowledge[i];
	}
	
	/**
	 * アイテム番号についての既知情報を更新する。
	 * @param i
	 * @param t
	 */
	public void setKnowledge(int i,boolean t)
	{
		this.ItemKnowledge[i]=t;
	}
	
	private  Const_Value 定数 = new Const_Value();
	
	private static ItemManager myself;
	private yukkurisim_main owner;
	
	public void Initialize()
	{
		ItemCount = new int[定数.アイテム最大番号+1];
		ItemKnowledge = new boolean[定数.アイテム最大番号+1];
		
		for(int i=0;i<定数.アイテム最大番号+1;i++)
		{
			ItemCount[i]=0;
			ItemKnowledge[i]=false;
		}
	}
	
	/**
	 * コンストラクタ
	 * @param own
	 */
	public ItemManager(yukkurisim_main own)
	{
		owner = own;
		Initialize();
	}

	/******* インスタンスを得る(親インスタンスと引き換え) ********/
	public static synchronized ItemManager Get_Instance( yukkurisim_main own )
	{
		if( myself == null)
		{
			myself = new ItemManager(own);
		}

		return myself;
	}

	/**
	 * アイテムを使用する。
	 * @param itemnum
	 */
	public void UseItem(int itemnum)
	{
		if(this.isKnown(itemnum))
		{	// 知ってて
			if(this.getItemCount(itemnum)>0)
			{ // かつ、持ってる
				if(itemnum==定数.アイテム_えほん)
				{
					System.out.println("アイテム使用：えほん");
				}
				else if(itemnum==定数.アイテム_おんみょうだま)
				{
					System.out.println("アイテム使用：おんみょうだま");
					
				}
				else if(itemnum==定数.アイテム_ほうき)
				{
					System.out.println("アイテム使用：ほうき");
					
				}
				else if(itemnum==定数.アイテム_ゆっくりフード)
				{
					System.out.println("アイテム使用：ゆっくりフード");
					
				}
				else if(itemnum==定数.アイテム_れみりゃぶくろ)
				{
					System.out.println("アイテム使用：れみりゃぶくろ");
					
				}
				else if(itemnum==定数.アイテム_オレンジジュース)
				{
					System.out.println("アイテム使用：オレンジジュース");
					
				}
				else if(itemnum==定数.アイテム_シュークリーム)
				{
					System.out.println("アイテム使用：シュークリーム");
					
				}
				else if(itemnum==定数.アイテム_ハサミ)
				{
					System.out.println("アイテム使用：ハサミ");
					
				}
				else if(itemnum==定数.アイテム_ブラシ)
				{
					System.out.println("アイテム使用：ブラシ");
					
				}
				else if(itemnum==定数.アイテム_ポン・デ・おやつ)
				{
					System.out.println("アイテム使用：ポンでおやつ");
					
				}
				else if(itemnum==定数.アイテム_穀物袋)
				{
					System.out.println("アイテム使用：穀物袋");
					
				}
				else if(itemnum==定数.アイテム_花火)
				{
					System.out.println("アイテム使用：花火");
					
				}
				else if(itemnum==定数.アイテム_要石のかけら)
				{
					System.out.println("アイテム使用：かなめいしのかけら");
					
				}
			}
		}
	}

	/**
	 * num番のアイテムを何個持っているか返す。
	 * @param num
	 * @return
	 */
	public int getItemCount(int num) {
		return ItemCount[num];
	}

	/**
	 * itemnum番のアイテムの個数をセットする。
	 * @param itemnum
	 * @param cnt
	 */
	public void setItemCount(int itemnum,int cnt) {
		ItemCount[itemnum] = cnt;
		if(ItemCount[itemnum]>0)
		{
			if(!(this.isKnown(itemnum)))
			{
				this.setKnowledge(itemnum, true);
			}
		}
	}
	
	/**
	 * itemnum番のアイテムの個数を一つ増やす
	 * @param itemnum
	 */
	public void setItemIncrement(int itemnum)
	{
		setItemCount(itemnum,getItemCount(itemnum)+1);
	}
	
	/**
	 * i番のアイテムの名前を返す。
	 * @param i
	 * @return
	 */
	public String getItemName(int i)
	{
		String  outmessage[] ={"穀物袋",
				"ゆっくりフード",
				"ポン・デ・おやつ",
				"シュークリーム",
				"オレンジジュース",
				"花火",
				"えほん",
				"おんみょうだま",
				"ほうき",
				"はさみ",
				"ブラシ",
				"れみりゃぶくろ",
				"要石のかけら"};
		return outmessage[i-1];
	}

	/**
	 * i番のアイテムに関する説明を返す。
	 * @param i
	 * @return
	 */
	public String getItemExplanation(int i)
	{
		String  outmessage[] ={"普通の飼料。雑草よりは栄養がある。",
				"味気はないが栄養はバッチリ。",
				"某有名お菓子のパチモノ。",
				"お菓子店で作られた若干お高めのもの。",
				"なぜかは知らないがかけるとゆっくりの傷がふさがる。",
				"スペルカードごっこができると評判のカード型花火。たまに怪我をする。",
				"賢くなれると一部のゆっくりに大人気。たまに傲慢になる。",
				"いわゆるゴムボール。陰陽の模様つき。たまに怪我をする。",
				"掃除用具。たまに怪我をする。",
				"ゆっくりの飾りの一部を破壊し、いじめを誘発できる。見た目は特に変わらないが…。",
				"選んだゆっくりの髪をすいてやることが出来る。",
				"別にれみりゃが入っているわけではないが、れみりゃの声を再生する。捕食種には無効。",
				"地震を起こしてゆっくり全員の体力を減らす。"};
		return outmessage[i-1];
	}
	
	/**
	 * getSaveDataは配列としていっぺんにデータを送るが
	 * setSaveDataは一つずつ受信する。
	 * @return
	 */
	public SaveData[] getSaveData()
	{
		SaveData mysave[] = new SaveData[定数.アイテム最大番号];
		for(int i=0;i<定数.アイテム最大番号;i++)
		{
			SaveData tmp = new SaveData();
			tmp.itemflag = true;
			tmp.itemindex = i+1;
			tmp.itemcount = getItemCount(i+1);
			tmp.ItemKnowledge = isKnown(i+1);
			mysave[i] = tmp;
		}
		return mysave;
	}
	
	public void setSaveData(SaveData mysave)
	{
		int i = mysave.itemindex;
		this.setItemCount(i, mysave.itemcount);
		this.setKnowledge(i, mysave.ItemKnowledge);
	}
}
