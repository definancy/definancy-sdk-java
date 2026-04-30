package com.definancy.sdk;

public class DID {
	private final String network;

	private final ID id;

	public DID(String network, ID id) {
		this.network = network;
		this.id = id;
	}

	public String getNetwork() {
		return network;
	}

	public ID getId() {
		return id;
	}

	@Override
	public String toString() {
		return "did:definancy:"+network+":"+id.toString();
	}
}
