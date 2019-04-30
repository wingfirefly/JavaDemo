package com.xxniu.hash;

public class HashSearch
{
    private final static int MAXSIZE = 20;
    private final static int NULLKEY = 1;
    private final static int DELKEY = 2;
    private final static int SUCCESS = 0;
    private final static int FAILED = 0xFFFFFFFF;

    /**
     * ���ҹ�ϣ�� �����ϣ����ó���ȡ�෨����f(key) = key mod p (p �� size) �����ͻ���ÿ��Ŷ�ַ������f2(key) = (f(key) + i) mod p (1 �� i �� size-1) haΪ��ϣ��pΪģ��sizeΪ��ϣ���С��keyΪҪ���ҵĹؼ���
     */
    public int searchHashTable(HashTable[] ha, int p, int size, int key)
    {
        int addr = key % p; // ���ó���ȡ�෨�ҹ�ϣ��ַ
        // ��������ͻ���ÿ��Ŷ�ַ������һ����ϣ��ַ
        while (ha[addr].key != NULLKEY && ha[addr].key != key)
        {
            addr = (addr + 1) % size;
        }
        if (ha[addr].key == key)
        {
            return addr; // ���ҳɹ�
        }
        else
        {
            return FAILED; // ����ʧ��
        }
    }

    /**
     * ɾ����ϣ���йؼ���Ϊkey�ļ�¼ �ҵ�Ҫɾ���ļ�¼�����ؼ�����Ϊɾ�����DELKEY
     */
    public int deleteHashTable(HashTable[] ha, int p, int size, int key)
    {
        int addr = 0;
        addr = searchHashTable(ha, p, size, key);
        if (FAILED != addr)
        { // �ҵ���¼
            ha[addr].key = DELKEY; // ����λ�õĹؼ�����ΪDELKEY
            return SUCCESS;
        }
        else
        {
            return NULLKEY; // ���Ҳ�����¼��ֱ�ӷ���NULLKEY
        }
    }

    /**
     * ��������Ĺؼ���key�����ϣ�� �ȵ��ò����㷨�����ڱ����ҵ�������Ĺؼ��֣������ʧ�ܣ� ���ڱ����ҵ�һ�����ŵ�ַ���򽫴�����Ľ����뵽���У������ɹ���
     */
    public void insertHashTable(HashTable[] ha, int p, int size, int key)
    {
        int i = 1;
        int addr = 0;
        addr = key % p; // ͨ����ϣ������ȡ��ϣ��ַ
        if (ha[addr].key == NULLKEY || ha[addr].key == DELKEY)
        { // ���û�г�ͻ��ֱ�Ӳ���
            ha[addr].key = key;
            ha[addr].count = 1;
        }
        else
        { // ����г�ͻ��ʹ�ÿ��Ŷ�ַ�������ͻ
            do
            {
                addr = (addr + 1) % size; // Ѱ����һ����ϣ��ַ
                i++;
            } while (ha[addr].key != NULLKEY && ha[addr].key != DELKEY);
            ha[addr].key = key;
            ha[addr].count = i;
        }
    }

    /**
     * ������ϣ�� �Ƚ���ϣ���и��ؼ�����գ�ʹ���ַΪ���ŵģ�Ȼ����ò����㷨�������Ĺؼ����������β��롣
     */
    public void createHashTable(HashTable[] ha, int[] list, int p, int size)
    {
        int i = 0;
        // ����ϣ���е����йؼ������
        for (i = 0; i < ha.length; i++)
        {
            ha[i].key = NULLKEY;
            ha[i].count = 0;
        }
        // ���ؼ����������β����ϣ����
        for (i = 0; i < list.length; i++)
        {
            this.insertHashTable(ha, p, size, list[i]);
        }
    }

    /**
     * �����ϣ��
     */
    public void displayHashTable(HashTable[] ha)
    {
        int i = 0;
        System.out.format("pos:", "pos");
        for (i = 0; i < ha.length; i++)
        {
            System.out.format("%4d", i);
        }
        System.out.println();
        System.out.format("key:");
        for (i = 0; i < ha.length; i++)
        {
            if (ha[i].key != NULLKEY)
            {
                System.out.format("%4d", ha[i].key);
            }
            else
            {
                System.out.format("    ");
            }
        }
        System.out.println();
        System.out.format("count:");
        for (i = 0; i < ha.length; i++)
        {
            if (0 != ha[i].count)
            {
                System.out.format("%4d", ha[i].count);
            }
            else
            {
                System.out.format("    ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        int[] list =
        {3, 112, 245, 27, 44, 19, 76, 29, 90};
        HashTable[] ha = new HashTable[MAXSIZE];
        for (int i = 0; i < ha.length; i++)
        {
            ha[i] = new HashTable();
        }
        HashSearch search = new HashSearch();
        search.createHashTable(ha, list, 19, MAXSIZE);
        search.displayHashTable(ha);
    }
}
