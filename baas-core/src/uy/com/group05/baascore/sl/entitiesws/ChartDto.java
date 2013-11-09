package uy.com.group05.baascore.sl.entitiesws;

import java.util.List;

public class ChartDto {
	
	private List<Integer> pedidosHttp;
	
	private List<Integer> dispRegistrados;

	private List<Integer> mensajesPushEnviados;

	public List<Integer> getPedidosHttp() {
		return pedidosHttp;
	}

	public void setPedidosHttp(List<Integer> pedidosHttp) {
		this.pedidosHttp = pedidosHttp;
	}

	public List<Integer> getDispRegistrados() {
		return dispRegistrados;
	}

	public void setDispRegistrados(List<Integer> dispRegistrados) {
		this.dispRegistrados = dispRegistrados;
	}

	public List<Integer> getMensajesPushEnviados() {
		return mensajesPushEnviados;
	}

	public void setMensajesPushEnviados(List<Integer> mensajesPushEnviados) {
		this.mensajesPushEnviados = mensajesPushEnviados;
	}

		
	
	
}
