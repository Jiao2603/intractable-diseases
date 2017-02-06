package ga.util;
/**
 * �v�Z�덷���l�����Ď����l�̔�r���s���D
 * <p>
 * ���̃N���X�̔�r���\�b�h�ł́C
 * ��r����2�����̍��̐�Βl���ݒ肳�ꂽ���e�덷�����ł���Γ������Ƃ݂Ȃ��܂��D
 * <p>
 * double��float�̌v�Z�ɂ͌덷�������̂Ȃ̂�
 * ==, <, >, <=, >=���Z�q�Ŕ�r�����C�K�����̃N���X���g���܂��傤�D
 * <p>
 * Double�N���X�̔�r���\�b�h�i{@link Double#compareTo(Double)}, {@link Double#equals(Object)}�j
 * �����e�덷�ɂ͑Ή����Ă��Ȃ����Ƃɒ��ӂ��Ă��������D
 * <p>
 * EPS�l��0�ɐݒ肵���ꍇ�C���̃N���X�̔�r���\�b�h��==, <, >, <=, >=���Z�q�̓���ƈ�v���܂��D
 * @since 59
 * @author hmkz
 */
public class TComparator {
	
	/** ���e�덷�̃f�t�H���g�l */
	public static final double DEFAULT_EPS = 1e-15;
	
	/** ���ݐݒ肳��Ă��鋖�e�덷 */
	private static double fEps = DEFAULT_EPS;
	
	/**
	 * ���e�덷��ݒ肷��D
	 * @param eps ���e�덷
	 * @since 59
	 * @author hmkz
	 */
	public static void setEps(double eps) {
		if (eps < 0) {
			throw new IllegalArgumentException("eps >= 0 is required.");
		}
		TComparator.fEps = eps;
	}
	
	/**
	 * ���ݐݒ肳��Ă��鋖�e�덷��Ԃ��D
	 * @return ���e�덷
	 * @since 59
	 * @author hmkz
	 */
	public static double getEps() {
		return fEps;
	}
	
	/**
	 * ���e�덷����a == b�D
	 * a��b�̍������e�덷�ȓ��ł���ΐ^�Ƃ݂Ȃ��D
	 * @param a ��r��������P
	 * @param b ��r��������Q
	 * @return a, b�̍��̐�Βl�����e�덷�ȓ��ł����true�C�����łȂ����false�D
	 * @since 59
	 * @author hmkz
	 */
	public static boolean equals(double a, double b) {
		return Math.abs(a - b) <= fEps;
	}
	
	/**
	 * ���e�덷����a > b�D
	 * a��b�������e�덷�ȏ�ɑ傫����ΐ^�Ƃ���D
	 * ���e�덷�̂��߁C�����ɂ�a > b�ł����Ă����̃��\�b�h��false��Ԃ����邱�Ƃɒ��ӂ��邱�ƁD
	 * @param a ��r��������P
	 * @param b ��r��������Q
	 * @return a��b��苖�e�덷�ȏ�傫�����true�C�����łȂ����false�D
	 * @since 59
	 * @author hmkz
	 */
	public static boolean isAGreaterThanB(double a, double b) {
		return a > b + fEps;
	}

	/**
	 * ���e�덷����a >= b�D
	 * a��b�Ɠ��������傫����ΐ^�Ƃ���D
	 * ���e�덷�̂��߁C�����ɂ�a < b�ł����Ă����̃��\�b�h��true��Ԃ����邱�Ƃɒ��ӂ��邱�ƁD
	 * @param a ��r��������P
	 * @param b ��r��������Q
	 * @return a��b�Ɠ��������傫�����true�C�����łȂ����false�D
	 * @since 59
	 * @author hmkz
	 */
	public static boolean isAGreaterEqualsB(double a, double b) {
		return a >= b - fEps;
	}

	/**
	 * ���e�덷����a < b�D
	 * a��b�������e�덷�ȏ�ɏ�������ΐ^�Ƃ���D
	 * ���e�덷�̂��߁C�����ɂ�a < b�ł����Ă����̃��\�b�h��false��Ԃ����邱�Ƃɒ��ӂ��邱�ƁD
	 * @param a ��r��������P
	 * @param b ��r��������Q
	 * @return a��b��苖�e�덷�ȏ㏬�������true�C�����łȂ����false�D
	 * @since 59
	 * @author hmkz
	 */
	public static boolean isALessThanB(double a, double b) {
		return a < b - fEps;
	}

	/**
	 * ���e�덷����a <= b�D
	 * a��b�Ɠ���������������ΐ^�Ƃ���D
	 * ���e�덷�̂��߁C�����ɂ�a > b�ł����Ă����̃��\�b�h��true��Ԃ����邱�Ƃɒ��ӂ��邱�ƁD
	 * @param a ��r��������P
	 * @param b ��r��������Q
	 * @return a��b�Ɠ����������������true�C�����łȂ����false�D
	 * @since 59
	 * @author hmkz
	 */
	public static boolean isALessEqualsB(double a, double b) {
		return a <= b + fEps;
	}
	
	/**
	 * ���̃N���X�̓C���X�^���X�����Ȃ��D
	 * @since 59
	 * @author hmkz
	 */
	protected TComparator() {}
}
