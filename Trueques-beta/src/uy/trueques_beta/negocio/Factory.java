package uy.trueques_beta.negocio;


public class Factory {
		private static UsuarioCtrl usuarioCtrl;
		private static ObjetoCtrl objetoCtrl;
		private static OfertaCtrl ofertaCtrl;
		private static TruequeCtrl truequeCtrl;

		private Factory() {
		}

		public static UsuarioCtrl getUsuarioCtrl() {
			if (usuarioCtrl == null) {
				
				usuarioCtrl = new UsuarioCtrl();
			}
			return usuarioCtrl;
		}
		
		public static ObjetoCtrl getObjetoCtrl() {
			if (objetoCtrl == null) {
				
				objetoCtrl = new ObjetoCtrl();
			}
			return objetoCtrl;
		}
		
		public static OfertaCtrl getOfertaCtrl() {
			if (ofertaCtrl == null) {
				
				ofertaCtrl = new OfertaCtrl();
			}
			return ofertaCtrl;
		}
		
		public static TruequeCtrl getTruequeCtrl() {
			if (truequeCtrl == null) {
				
				truequeCtrl = new TruequeCtrl();
			}
			return truequeCtrl;
		}

}
