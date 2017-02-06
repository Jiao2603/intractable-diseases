package ga.util;


import java.io.*;
import java.util.Random;

/**
 * �����N���X�D�V���O���g���p�^�[�����p�����Ă���D
 * @since 2
 * @author yamhan, isao
 * @author hmkz
 */
// 2007/07/21 hmkz ���\�b�h����getDouble���next�`���D�݂ł��Dgetter�łȂ����̂�get�`�ƌĂԂ͕̂s���R�ł́H
public class TMyRandom {

	/** �����W�F�l���[�^�D1�������݂��Ȃ�(Singleton)�D*/
	private static TMyRandom fInstance;
	
	/** ���������� */
	private Random fRandom;
	
	private TMyRandom() {
		fRandom = new TMersenneTwister19937();	
	}
	
	private TMyRandom(long seed){
		fRandom = new TMersenneTwister19937(seed);
	}
	
	/**
	 * �����̎���Z�b�g����D
	 * @param seed �����̎�
	 * @since 2
	 * @author yamhan, isao
	 */
	public void setSeed(long seed) {
		fRandom.setSeed(seed);
	}
	
	/**
	 * TMyRandom�^�̃C���X�^���X�𓾂�D<BR>
	 * �����̎�́C���̃��\�b�h���s���̎��Ԃɂ�茈�肷��D
	 * @return TMyRandom�̃C���X�^���X
	 * @since 2
	 * @author yamhan, isao
	 */
	public static TMyRandom getInstance() {
		if(fInstance == null) {
			fInstance = new TMyRandom();
		}
		return fInstance;	
	}
	
	/**
	 * TMyRandom�^�̃C���X�^���X�𓾂�D<BR>
	 * �����̎�́Cseed�ɂȂ�D
	 * @param seed �����̎�
	 * @return TMyRandom�̃C���X�^���X
	 * @since 2
	 * @author yamhan, isao
	 */
	public static TMyRandom getInstance(long seed) {
		if(fInstance == null) {
			fInstance = new TMyRandom(seed);
		} else {
			fInstance.setSeed(seed);
		}
		return fInstance;	
	}

	/**
	 * �w�肳�ꂽ�I�u�W�F�N�g�C���[�W���畜�����ꂽTMyRandom�^�̃C���X�^���X��Ԃ��D
	 * @param filename �I�u�W�F�N�g�C���[�W���i�[����Ă���t�@�C����
	 * @return TMyRandom�^�̃C���X�^���X
	 * @since 2
	 * @author yamhan, isao
	 */
	public static TMyRandom getInstance(String filename) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
			fInstance = (TMyRandom)ois.readObject();
			ois.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(5);
		}
		return fInstance;
	}
	
	/**
	 * �I�u�W�F�N�g�C���[�W���t�@�C���֏o�͂���D
	 * @param filename �o�͐�̃t�@�C����
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @since 2
	 * @author yamhan, isao
	 */
	public static void writeObjectImage(String filename) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		writeObjectImage(fos);
		fos.close();
	}
	
	/**
	 * �I�u�W�F�N�g�C���[�W���X�g���[���֏o�͂���D
	 * @param os �o�͐�̃X�g���[��
	 * @throws IOException
	 * @since 2
	 * @author yamhan, isao
	 */
	public static void writeObjectImage(OutputStream os) throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(os);
		oos.writeObject(fInstance);
	}
	
	/**
	 * min < = randomDouble < = max �ƂȂ��������randomDouble��Ԃ��D
	 * @param min �����̍ŏ��l
	 * @param max �����̍ő�l
	 * @return ��������
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getDouble(double min, double max) {
		double randomDouble = 0.0;
		randomDouble = fRandom.nextDouble();
		randomDouble *= (max - min);
		randomDouble += min;
		return randomDouble;	
	}	

	/**
	 * min <= randomInt <= max �ƂȂ鐮������randomInt��Ԃ��D
	 * @param min �����̍ŏ��l
	 * @param max �����̍ő�l
	 * @return ��������
	 * @since 2
	 * @author yamhan, isao
	 */
	public int getInteger(int min, int max) {
		int randomInt = fRandom.nextInt(max-min+1);
		randomInt += min;
		return randomInt;
	}
	
	/**
	 * ���K������Ԃ��D
	 * @param mean ���ϒl
	 * @param sigma �W���΍�
	 * @return ���K����
	 * @since 2
	 * @author yamhan, isao
	 */
	public double getNormalDistributedNumber(double mean, double sigma) {
		return mean + sigma * fRandom.nextGaussian();
	}

	/**
	 * true�܂���false���ܕ��ܕ��̊m���ŕԂ��D
	 * @return true�܂���false
	 * @since 48
	 * @author hmkz
	 */
	public boolean getBoolean() {
		return fRandom.nextBoolean();
	}
	
	/**
	 * �z����V���b�t������D
	 * �v�f�͈�l�ɓ���ւ��D
	 * @param buf �V���b�t�������z��
	 * @since 19
	 * @author hmkz
	 */
	public void shuffle(int[] buf) {
		for (int i = 0; i < buf.length; i++) {
			int r = getInteger(i, buf.length - 1);
			int tmp = buf[i];
			buf[i] = buf[r];
			buf[r] = tmp;
		}
	}

	/**
	 * �z����V���b�t������D
	 * �v�f�͈�l�ɓ���ւ��D
	 * @param buf �V���b�t�������z��
	 * @since 48
	 * @author hmkz
	 */
	public void shuffle(double[] buf) {
		for (int i = 0; i < buf.length; i++) {
			int r = getInteger(i, buf.length - 1);
			double tmp = buf[i];
			buf[i] = buf[r];
			buf[r] = tmp;
		}
	}

	/**
	 * min <= n <= max �ƂȂ鐮������n��buf�̗v�f�����߂�D
	 * @param buf ������������������
	 * @param min �����̍ŏ��l�i���̒l���܂ށj
	 * @param max �����̍ő�l�i���̒l���܂ށj
	 * @since 48
	 * @author hmkz
	 */
	public void fill(int[] buf, int min, int max) {
		for (int i = 0; i < buf.length; i++) {
			buf[i] = getInteger(min, max);
		}
	}

	/**
	 * min <= d <= max �ƂȂ闐��d��buf�̗v�f�����߂�D
	 * @param buf ������������������
	 * @param min �����̍ŏ��l�i���̒l���܂ށj
	 * @param max �����̍ő�l�i���̒l���܂ށj
	 * @since 48
	 * @author hmkz
	 */
	public void fill(double[] buf, double min, double max) {
		for (int i = 0; i < buf.length; i++) {
			buf[i] = getDouble(min, max);
		}
	}
}
