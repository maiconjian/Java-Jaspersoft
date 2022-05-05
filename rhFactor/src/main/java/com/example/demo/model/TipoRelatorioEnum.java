package com.example.demo.model;

public enum TipoRelatorioEnum {
	
	FORNECEDOR(1), EMPREEITEIRO(2),FATURADOS(3),AGUARDANDO_FATURAMENTO(4),
	CONTAS_PAGAS(5),CONTAS_A_PAGAR(6),MEDICAO_OBRA(7),FECHAMENTO_OBRA(8);
	
	private final int value;

	private TipoRelatorioEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
