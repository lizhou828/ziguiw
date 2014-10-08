package com.zigui.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.zigui.utils.AlipayConfig;
import com.zigui.utils.AlipaySubmit;





/* *
 *����AlipayService
 *���ܣ�֧�������ӿڹ�����
 *��ϸ������֧�������ӿ��������
 *�汾��3.2
 *�޸����ڣ�2011-03-17
 *˵����
 *���´���ֻ��Ϊ�˷����̻����Զ��ṩ��������룬�̻����Ը���Լ���վ����Ҫ�����ռ����ĵ���д,����һ��Ҫʹ�øô��롣
 *�ô������ѧϰ���о�֧�����ӿ�ʹ�ã�ֻ���ṩһ���ο���
 */

public class AlipayService {
    
    /**
     * ֧�����ṩ���̻��ķ���������URL(��)
     */
    private static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";

    /**
     * ���켴ʱ���ʽӿ�
     * @param sParaTemp ��������
     * @return �?�ύHTML��Ϣ
     */
    public static String create_direct_pay_by_user(Map<String, String> sParaTemp) {

    	//���ӻ�����
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("return_url", AlipayConfig.return_url);
        sParaTemp.put("notify_url", AlipayConfig.notify_url);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);

        String strButtonName = "ȷ��";

        return AlipaySubmit.buildForm(sParaTemp, ALIPAY_GATEWAY_NEW, "get", strButtonName);
    }

    /**
     * ���ڷ����㣬���ýӿ�query_timestamp����ȡʱ����Ĵ��?��
     * ע�⣺Զ�̽���XML���?��������Ƿ�֧��SSL�������й�
     * @return ʱ����ַ�
     * @throws IOException
     * @throws DocumentException
     * @throws MalformedURLException
     */
    public static String query_timestamp() throws MalformedURLException,
                                                        DocumentException, IOException {

        //�������query_timestamp�ӿڵ�URL��
        String strUrl = ALIPAY_GATEWAY_NEW + "service=query_timestamp&partner=" + AlipayConfig.partner;
        StringBuffer result = new StringBuffer();

        SAXReader reader = new SAXReader();
        Document doc = reader.read(new URL(strUrl).openStream());

        List<Node> nodeList = doc.selectNodes("//alipay/*");

        for (Node node : nodeList) {
            // ��ȡ���ֲ���Ҫ��������Ϣ
            if (node.getName().equals("is_success") && node.getText().equals("T")) {
                // �ж��Ƿ��гɹ���ʾ
                List<Node> nodeList1 = doc.selectNodes("//response/timestamp/*");
                for (Node node1 : nodeList1) {
                    result.append(node1.getText());
                }
            }
        }

        return result.toString();
    }
}
