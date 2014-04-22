package com.cacard.collections;

public class HashTableEntry {

	public int key;
	public int value;
	public HashTableEntry next;
	
	public HashTableEntry(int k,int v,HashTableEntry n)
	{
		this.key=k;
		this.value=v;
		this.next=n;
	}
	
}
