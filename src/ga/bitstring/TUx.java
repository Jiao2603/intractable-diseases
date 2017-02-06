package ga.bitstring;

import ga.util.TMyRandom;
import ga.bitstring.TBitString;


/**
 * ��l����
 * @since 2
 * @author isao
 */
public class TUx {
	
	/** �e�P */
	private TBitString fParent1;
	
	/** �e�Q */
	private TBitString fParent2;

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TUx() {
		fParent1 = null; 
		fParent2 = null;
	}
	
	/**
	 * ���e��ݒ肷��D
	 * @param p1 �e�P
	 * @param p2 �e�Q
	 * @since 2
	 */
	public void setParents(TBitString p1, TBitString p2) {
		fParent1 = p1;
		fParent2 = p2;
	}
	
	/**
	 * �q�̂𐶐�����D
	 * @param kid1 �q�P
	 * @param kid2 �q�Q
	 * @since 2
	 */
	public void doIt(TBitString kid1, TBitString kid2) {
		TMyRandom rand = TMyRandom.getInstance();
		int length = fParent1.getLength();
		kid1.setLength(length);
		kid2.setLength(length);
		for (int i = 0; i < length; ++i) {
			if (rand.getInteger(0, 1) == 0) {
				kid1.setData(i, fParent1.getData(i));
				kid2.setData(i, fParent2.getData(i));
			} else {
				kid1.setData(i, fParent2.getData(i));
				kid2.setData(i, fParent1.getData(i));				
			}
		}
	}
	
}
