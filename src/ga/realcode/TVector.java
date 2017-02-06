package ga.realcode;

import ga.util.TComparator;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * �����x�N�g��
 * @since 2
 * @author yamhan, isao
 * @author hmkz
 */
public class TVector {
	
	/** �x�N�g���̗v�f */
	private double[] fArray;	

	/**
	 * �R���X�g���N�^
	 * @since 2
	 * @author yamhan, isao
	 * @author hmkz {@link #TVector(int)}�ɈϏ�
	 */
	public TVector() {
		this(0);
	}
	
	/**
	 * �R���X�g���N�^
	 * @param dimension 
	 * @since 2
	 * @author yamhan, isao
	 */
	public TVector(int dimension) {
		fArray = new double [dimension];
	}
	
	/**
	 * �R���X�g���N�^
	 * @param array �����l�̔z��
	 * @since 2
	 * @author yamhan, isao
	 * @author hmkz {@link Arrays#copyOf(double[], int)}���g���ď�������
	 */
	public TVector(double[] array) {
		fArray = Arrays.copyOf(array, array.length);
	}

	/**
	 * �R�s�[�R���X�g���N�^
	 * @param src �R�s�[��
	 * @since 2
	 * @author yamhan, isao
	 * @author hmkz {@link #TVector(double[])}�ɈϏ�
	 */
	public TVector(TVector src) {
		this(src.fArray);
	}
	
	/**
	 * �x�N�g��src����p�����[�^���R�s�[����D
	 * @param src �R�s�[���̃x�N�g��
	 * @since 2
	 * @author yamhan, isao
	 * @author hmkz {@link System#arraycopy(Object, int, Object, int, int)}���g���ď�������
	 */
	public TVector copyFrom(TVector src) {
		if(fArray.length != src.fArray.length) {		
			fArray = new double[src.fArray.length];
		}
		System.arraycopy(src.fArray, 0, fArray, 0, src.fArray.length);
		return this;
	}
	
	/**
	 * �x�N�g���̃p�����[�^���������ފ֐��D
	 * @param pw �������ݐ�
	 * @since 2
	 * @author yamhan, isao
	 */
	public void writeTo(PrintWriter pw) {
		pw.println(fArray.length);
		for(int i = 0; i < fArray.length; i++) {
			pw.print(fArray[i] + " ");
		}
		pw.println();			
	}
	
	/**
	 * �x�N�g���̃p�����[�^��ǂݍ��ފ֐��D
	 * @param br �ǂݍ��݌�
	 * @throws Exception �ǂݍ��ݎ���Exception
	 * @since 2
	 * @author yamhan, isao
	 */
	public void readFrom(BufferedReader br) throws Exception {
		int dimension = Integer.parseInt(br.readLine().trim());
		String[] data = br.readLine().split("\\s");
		fArray = new double[dimension];
		for (int i = 0; i < fArray.length; i++) {
			fArray[i] = Double.parseDouble(data[i]);
		}
	}
	
	/**
	 * �W���o�͂փx�N�g���̓��e�������o���D
	 * @since 2
	 * @author yamhan
	 */
	public void printOn() {
		System.out.println(fArray.length);
		for (int i = 0; i < fArray.length; ++i)
			System.out.print(fArray[i] + " ");
		System.out.println();
	}
	
	/**
	 * ���������Z�b�g����D
	 * @param dimension ������
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setDimension(int dimension) {
		if(fArray.length == dimension) {		
			return;		
		}
		fArray = new double[dimension];		
	}
		
	/**
	 * �������𓾂�D
	 * @return ������
	 * @since 2
	 * @author yamhan, isao
	 */	
	public int getDimension() {
		return fArray.length;
	}
	
	/**
	 * �y�N�g���Ƀf�[�^���Z�b�g����D
	 * @param index �Z�b�g����f�[�^�̈ʒu
	 * @param data �f�[�^
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setData(int index, double data) {
		fArray[index] = data;
	}
	
	/**
	 * �x�N�g���̃f�[�^�𓾂�D
	 * @param index �������f�[�^�̓Y����
	 * @return �������f�[�^
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getData(int index) {
		return fArray[index];
	}
	
	/**
	 * �x�N�g�����m�𑫂��D
	 * @param src �����x�N�g��
	 * @since 2
	 * @author yamhan, isao
	 */
	public TVector add(TVector src) {
		for(int i = 0; i < fArray.length; i++) {
			fArray[i] += src.fArray[i];
		}
		return this;
	}
	
	/**
	 * �x�N�g�����m�������D
	 * @param src �����x�N�g��
	 * @since 2
	 * @author yamhan, isao
	 */
	public TVector sub(TVector src) {
		for(int i = 0; i < fArray.length; i++) {
			fArray[i] -= src.fArray[i];
		}
		return this;
	}
	
	/**
	 * �x�N�g���������{����D
	 * @param a �x�N�g���̔{��
	 * @since 2
	 * @author yamhan, isao
	 */
	public TVector scalarProduct(double a) {
		for(int i = 0; i < fArray.length; i++) {
			fArray[i] *= a;
		}
		return this;
	}
	
	/**
	 * �x�N�g���̓��ς�Ԃ��D
	 * @param y �x�N�g��
	 * @return y�Ƃ̓���
	 * @since 2
	 * @author yamhan, isao
	 */
	public double innerProduct(TVector y) {
		double result = 0.0;
		for(int i = 0; i < fArray.length; i++) {
			result += fArray[i] * y.fArray[i];
		}
		return result;	
	}
	
	/**
	 * �x�N�g���̒�����Ԃ��D
	 * @return �x�N�g���̒����D
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getLength() {
		double result = 0.0;
		for(int i = 0; i < fArray.length; i++) {
			result += fArray[i] * fArray[i];
		}
		return Math.sqrt(result);
	}
	
	/**
	 * �P�ʃx�N�g��������D
	 * @since 2
	 * @author yamhan, isao
	 */
	public TVector unitVector() {
		double length = getLength();
		for(int i = 0; i < fArray.length; i++) {	
			fArray[i] /= length;
		}
		return this;
	}
 
 	/**
 	 * �x�N�g���̊e�v�f��0.0�ŏ���������D
	 * @since 2
	 * @author yamhan, isao
 	 */
	public TVector zeroVector() {
		for(int i = 0; i < fArray.length; i++) {
			fArray[i] = 0.0; 
		}
		return this;
	}
	
	/**
	 * �x�N�g��o�Ƃ̃��[�N���b�h������TComparator.getEps()�ȉ��Ȃ��true�D�����łȂ����false
	 * ���e�덷��TComparator�N���X��p���Ċm�F�E�ݒ�ł���D
	 * @see TComparator#getEps()
	 * @see TComparator#setEps(double)
	 * @since 49
	 * @author hmkz
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TVector)) {
			return false;
		}
		TVector v = (TVector) o;
		if (fArray.length != v.fArray.length) {
			return false;
		}
		return distance(v) <= TComparator.getEps();
	}

	/**
	 * ���̃x�N�g���Ǝw�肳�ꂽ�x�N�g��v�Ƃ̃��[�N���b�h������Ԃ��D
	 * �Q�̃x�N�g���̎������������ł��邱�ƁD
	 * @param v ���̃x�N�g���Ɠ��������������x�N�g��
	 * @return ���[�N���b�h����
	 * @since 87
	 * @author hmkz
	 */
	public double distance(TVector v) {
		if (fArray.length != v.fArray.length) {
			throw new IllegalArgumentException(
					"Illegal dimension of v. expected: " + fArray.length + ", but: " + v.fArray.length);
		}

		double sum = 0.0;
		for (int i = 0; i < fArray.length; i++) {
			double xi = fArray[i] - v.fArray[i];
			sum += xi * xi;
		}
		return Math.sqrt(sum);
	}
}
