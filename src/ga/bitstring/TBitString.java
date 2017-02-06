package ga.bitstring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * �r�b�g�X�g�����O
 * @since 2
 * @author isao
 */
public class TBitString {
	
	/** �f�[�^ */
	private int[] fArray;
	
	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TBitString() {
		this(1);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param length �r�b�g�X�g�����O��
	 * @since 2
	 */
	public TBitString(int length) {
		fArray = new int [length];
	}
	
	/**
	 *
	 * @param str
	 * @since 2
	 */
	public TBitString(String str) {
		fArray = new int [1];
		fromString(str);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param src �R�s�[��
	 * @since 2
	 */
	public TBitString(TBitString src) {
		fArray = new int [src.fArray.length];
		for (int i = 0; i < src.fArray.length; ++i) {
			fArray[i] = src.fArray[i];
		}
	}
	
	/**
	 * �R�s�[
	 * @param src �R�s�[��
	 * @since 2
	 */
	public void copyFrom(TBitString src) {
		setLength(src.fArray.length);
		for (int i = 0; i < src.fArray.length; ++i) {
			fArray[i] = src.fArray[i];
		}
	}
	
	/**
	 * �W���o�͂֏o�͂���D
	 * @since 2
	 */
	public void printOn() {
		System.out.println(toString());
	}
	
	/**
	 * ���̓X�g���[������f�[�^��ǂݍ���
	 * @param br ���̓X�g���[��
	 * @throws IOException �ǂݍ��݂Ɏ��s�����ꍇ�ɓ�������D
	 * @since 2
	 */
	public void readFrom(BufferedReader br) throws IOException {
		fromString(br.readLine());
	}
	
	/**
	 * �o�̓X�g���[���փf�[�^�������o���D
	 * @param pw �o�̓X�g���[��
	 * @throws IOException �����o���Ɏ��s�����ꍇ�ɓ�������D
	 * @since 2
	 */
	public void writeTo(PrintWriter pw) {
		pw.println(toString());
	}
	
	/**
	 * �r�b�g�X�g�����O����ݒ肷��D
	 * @param length �r�b�g�X�g�����O��
	 * @since 2
	 */
	public void setLength(int length) {
		if (fArray.length == length) {
			return;			
		}
		fArray = new int [length];
	}
	
	/**
	 * �r�b�g�X�g�����O����Ԃ��D
	 * @return �r�b�g�X�g�����O��
	 * @since 2
	 */
	public int getLength() {
		return fArray.length;
	}

	/**
	 * index�Ԗڂ̃f�[�^��Ԃ��D
	 * @param index �Y����
	 * @return �f�[�^ (0 or 1)
	 * @since 2
	 */
	public int getData(int index) {
		return fArray[index];
	}
	
	/**
	 * index�Ԗڂ�data��ݒ肷��D
	 * @param index �Y����
	 * @param data �f�[�^ (0 or 1)
	 * @since 2
	 */
	public void setData(int index, int data) {
		fArray[index] = data;
	}

	/**
	 * �S�Ẵr�b�g��data��ݒ肷��D
	 * @param data �f�[�^ (0 or 1)
	 * @since 2
	 */
	public void setToAllBits(int data) {
		for (int i = 0; i < fArray.length; ++i) {
			fArray[i] = data;	
		}
	}
	
	/**
	 * index�Ԗڂ�data�𔽓]����D
	 * @param index �Y����
	 * @since 2
	 */
	public void flip(int index) {
		fArray[index] = (fArray[index] == 0 ? 1 : 0);
	}
	
	/**
	 * �S�Ẵr�b�g�𔽓]����D
	 * @since 2
	 */
	public void flipAllBits() {
		for (int i = 0; i < fArray.length; ++i) {
			flip(i);
		}
	}
	
	/**
	 * x�Ƃ̘_���ς��Ƃ�D
	 * @param x �r�b�g�X�g�����O
	 * @since 2
	 */
	public void and(TBitString x) {
		for (int i = 0; i < fArray.length; ++i) {
			fArray[i] *= x.fArray[i];
		}
	}
	
	/**
	 * x�Ƃ̘_���a���Ƃ�D
	 * @param x �r�b�g�X�g�����O
	 * @since 2
	 */
	public void or(TBitString x) {
		for (int i = 0; i < fArray.length; ++i) {
			int tmp = fArray[i] + x.fArray[i];
			fArray[i] = tmp > 0 ? 1 : 0;
		}
	}
	
	/**
	 * x�Ƃ̔r���I�_���a���Ƃ�D
	 * @param x �r�b�g�X�g�����O
	 * @since 2
	 */
	public void xor(TBitString x) {
		for (int i = 0; i < fArray.length; ++i) {
			if (fArray[i] == x.fArray[i]) {
				fArray[i] = 0;
			} else {
				fArray[i] = 1;
			}
		}		
	}
	
	/**
	 * 0��1����Ȃ镶����Ńf�[�^������������D
	 * @param str 0��1����Ȃ镶����
	 * @since 2
	 */
	public void fromString(String str) {
		setLength(str.length());
		for (int i = 0; i < str.length(); ++i) {
			fArray[i] = str.charAt(i) == '0' ? 0 : 1;
		}
	}

	/**
	 * ������ɕϊ�����D
	 * @return 0��1����Ȃ镶����
	 * @since 2
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(fArray.length);
		for (int i = 0; i < fArray.length; ++i) {
			if (fArray[i] == 0) {
				sb.append('0');
			} else {
				sb.append('1');
			}
		}
		return sb.toString();
	}

}
