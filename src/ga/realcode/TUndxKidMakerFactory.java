package ga.realcode;

import ga.core.IKidMaker;
import ga.core.IKidMakerFactory;
import ga.realcode.TUndxKidMaker;

/**
 * UNDX�t�@�N�g��
 * @since 2
 * @author isao
 */
public class TUndxKidMakerFactory implements IKidMakerFactory {

	private double fAlpha;
	
	private double fBeta;
	
	/**
	 * �R���X�g���N�^
	 * @param prop �v���p�e�B
	 * @since 2
	 * @author isao
	 */
	public TUndxKidMakerFactory(double alpha, double beta) {
		fAlpha = alpha;
		fBeta = beta;
	}
	
	/**
	 * @see IKidMakerFactory#create()
	 * @since 2
	 * @author isao
	 */
	public IKidMaker create() {
		IKidMaker result =  new TUndxKidMaker(fAlpha, fBeta);
		return result;
	}

}
