package ga.util;


/**
 * ���[���b�g
 * @since 2
 * @author yamhan, isao
 */
public class TRoulette {

	/** �X���b�g�̐� */
	private int fNoOfSlots;
	
	/** ���݂̃X���b�g�̈ʒu */
	private int fCurrentSlotIndex;
	
	/** �X���b�g */
	private double[] fSlots;

	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author yamhan, isao
	 */
	public TRoulette() {
		fCurrentSlotIndex = 0;
		setNoOfSlots(0);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param noOfSlots ���[���b�g�̃X���b�g�̐�
	 * @since 2
	 * @author yamhan, isao
	 */
	public TRoulette(int noOfSlots) {
		fCurrentSlotIndex = 0;
		setNoOfSlots(noOfSlots);
	}
	
	/**
	 * ���[���b�gsrc�̃p�����[�^���R�s�[����D
	 * @param src �R�s�[���̃��[���b�g
	 * @since 2
	 * @author yamhan, isao
	 */
	public void copyFrom(TRoulette src) {
		setNoOfSlots(src.fNoOfSlots);
		fCurrentSlotIndex = src.fCurrentSlotIndex;
		for(int i = 0; i < fNoOfSlots; i++)
			fSlots[i] = src.fSlots[i];
	}	
	
	/**
	 * �X���b�g�����Z�b�g����D
	 * @param noOfSlots �X���b�g��
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setNoOfSlots(int noOfSlots) {
		if(fNoOfSlots == noOfSlots)
			return;
		resetCurrentSlotIndex();
		fSlots = new double[noOfSlots];
		fNoOfSlots = noOfSlots;				
	}
	
	/**
	 * �X���b�g����Ԃ��D
	 * @return �X���b�g��
	 * @since 2
	 * @author yamhan, isao
	 */
	public int getNoOfSlots() {
		return fNoOfSlots;
	}
		
	/**
	 * ���݂̃X���b�g�̈ʒu�����Z�b�g����D<BR>
	 * �J�����g�X���b�g��0�ɂ���D
	 * @since 2
	 * @author yamhan, isao
	 */	
	public void resetCurrentSlotIndex() {
		fCurrentSlotIndex = 0;
	}
	
	/**
	 * ���݂̃X���b�g�̈ʒu��Ԃ��D
	 * @return ���݂̃X���b�g�̈ʒu
	 * @since 2
	 * @author yamhan, isao
	 */
	public int getCurrentSlotIndex() {
		return fCurrentSlotIndex;
	}
	
	/**
	 * �X���b�g�ɒl���Z�b�g����D
	 * @param value �Z�b�g����l
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setValueToSlot(double value) {
		if(fCurrentSlotIndex == 0) {
			fSlots[fCurrentSlotIndex] = value;
		} else{		
			fSlots[fCurrentSlotIndex] = 
				fSlots[fCurrentSlotIndex-1] + value;
		}
		fCurrentSlotIndex++;		
	}
	
	/**
	 * index�Ԗڂ̃X���b�g�̒l��Ԃ��D
	 * @param index �l�𓾂����X���b�g�̈ʒu
	 * @return �X���b�g�̒l
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getSlotValue(int index) {
		return fSlots[index];
	}
	
	/**
	 * ���[���b�g���񂵂āC�����_���ɔԍ���Ԃ�
	 * @return �I�΂ꂽ�ԍ�
	 * @since 2
	 * @author yamhan, isao
	 */
	public int doIt() {
		int  selectedIndex;
		double randomNum = 0.0;
		randomNum = TMyRandom.getInstance().getDouble(0.0, fSlots[fCurrentSlotIndex-1]);
		for(selectedIndex = 0; selectedIndex < fNoOfSlots; selectedIndex++)
			if(fSlots[selectedIndex] > randomNum)
				return selectedIndex;	
		return selectedIndex;
	}
}
