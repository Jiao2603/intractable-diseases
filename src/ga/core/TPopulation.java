package ga.core;


import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * �W�c
 * @since 2
 * @author yamhan, isao
 * @author kinoshita
 * @author hmkz
 */
public class TPopulation {

	/** �̂̔z�� */
	private IIndividual[] fArray = new IIndividual[0];
	
	/** �̂𐶐����� */
	private IIndividualFactory fIndividualFactory = null;

	/**
	 * �R���X�g���N�^
	 * @since 2
	 */
	public TPopulation() {
	}
	
	/**
	 * �R���X�g���N�^
	 * @param factory �̃t�@�N�g��
	 * @since 2
	 */
	public TPopulation(IIndividualFactory factory) {
		this(factory, 0);
	}

	/**
	 * �R���X�g���N�^
	 * @param factory �̃t�@�N�g��
	 * @param size �W�c�T�C�Y
	 * @since 2
	 */
	public TPopulation(IIndividualFactory factory, int size) {
		fIndividualFactory = factory;
		fArray = new IIndividual[size];
		for(int i = 0; i < size; i++) {
			fArray[i] = fIndividualFactory.create();
		}	
	}
	
	/**
	 * �R�s�[�R���X�g���N�^
	 * @param src �R�s�[��
	 * @since 2
	 */
	public TPopulation(TPopulation src) {
		fIndividualFactory = src.fIndividualFactory;
		if(fArray.length != src.fArray.length) {		
			fArray = new IIndividual[src.fArray.length];
			for(int i = 0; i < src.fArray.length; i++) {
				fArray[i] = fIndividualFactory.create();
			}
		}
		for(int i = 0; i < src.fArray.length; i++) {
			fArray[i].copyFrom(src.fArray[i]);	
		}	
	}
	
	/**
	 * ��src�̃p�����[�^�������ɃR�s�[����D
	 * @param src �R�s�[��
	 * @since 2
	 */
	public void copyFrom(TPopulation src) {
		fIndividualFactory = src.fIndividualFactory;
		if(fArray.length != src.fArray.length) {		
			fArray = new IIndividual[src.fArray.length];
			for(int i = 0; i < src.fArray.length; i++) {
				fArray[i] = fIndividualFactory.create();
			}
		}
		for(int i = 0; i < src.fArray.length; i++) {
			fArray[i].copyFrom(src.fArray[i]);	
		}	
	}

	/**
	 * �̂̃p�����[�^���������ށD
	 * @param pw �������ݐ�
	 * @throws Exception �������ݎ���Exception
	 * @since 2
	 */
	public void writeTo(PrintWriter pw) throws Exception {
		pw.println(fIndividualFactory.getClass().getName());
		pw.println(fArray.length);
		for(int i = 0; i < fArray.length; i++) {
			fArray[i].writeTo(pw);
		}
	}
	
	/**
	 * �̂̃p�����[�^��ǂݍ��ށD
	 * @param br �ǂݍ��݌�
	 * @throws Exception �ǂݍ��ݎ���Exception
	 * @since 2
	 */
	public void readFrom(BufferedReader br) throws Exception {
		String className = br.readLine();
		fIndividualFactory = (IIndividualFactory)Class.forName(className).newInstance();
		StringTokenizer st = new StringTokenizer(br.readLine());	
		int size = Integer.parseInt(st.nextToken());
		fArray = new IIndividual[size];
		for(int i = 0; i < size; i++) {
			fArray[i] = fIndividualFactory.create();	
			fArray[i].readFrom(br);
		}		
	}
	
	/**
	 * �W�c�T�C�Y���Z�b�g����D
	 * @param size �W�c�T�C�Y
	 * @since 2
	 */
	public void setSize(int size) {
		if(fArray.length == size) {
			return;
		}
		fArray = new IIndividual[size];
		for(int i = 0; i < size; i++) {
			fArray[i] = fIndividualFactory.create();	
		}
	}
	
	/**
	 * �W�c�T�C�Y�𓾂�D
	 * @return �W�c�T�C�Y
	 * @since 2
	 */
	public int getSize() {
		return fArray.length;
	}
	
	/**
	 * �W�c���Ɍ̂��Z�b�g����D
	 * @param index �Z�b�g����Y����
	 * @param src �Z�b�g�����
	 * @since 2
	 */
	public void setIndividual(int index, IIndividual src) {
		fArray[index] = src;
	}

	/**
	 * �W�c���̌̂𓾂�D
	 * @param index �������̂̓Y����
	 * @return ��
	 * @since 2
	 */
	public IIndividual getIndividual(int index) {
		return fArray[index];
	}

	/**
	 * �̃t�@�N�g����Ԃ��D
	 * @return �̃t�@�N�g��
	 * @since 2
	 */
	public IIndividualFactory getIndividualFactory() {
		return fIndividualFactory;
	}

	/**
	 * �̃t�@�N�g����ݒ肷��D
	 * @param factory �̃t�@�N�g��
	 * @since 2
	 */
	public void setIndividualFactory(IIndividualFactory factory) {
		fIndividualFactory = factory;
	}

	/**
	 * �̃t�@�N�g���ƏW�c���̊e�̂̂��ׂĂ��������ꍇ��true�C�����łȂ����false�D
	 * @since 55
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof TPopulation)) {
			return false;
		}
		TPopulation pop = (TPopulation) o;
		if (fIndividualFactory == null) {
			if (pop.fIndividualFactory != null) {
				return false;
			}
		} else {
			if (!fIndividualFactory.equals(pop.fIndividualFactory)) {
				return false;
			}
		}
		return Arrays.equals(fArray, pop.fArray);
	}
}
