package com.example.samuraitravel;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class StripeApiExample {

    public static void main(String[] args) {
        String url = "https://api.stripe.com/v1/charges";  // APIエンドポイント
        String secretKey = "sk_test_51PZr0JLJ2LcWpnobTbGpa4gBqMnPWYbz2LJmusmBfUYwqrGl6BRXDCFZZ9P8AOP6GWgltscFzklD5GwbxJA1A4zT00M6eM0sqC";  // あなたのStripeシークレットキー

        // HttpClientのインスタンスを作成
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // POSTリクエストを作成
            HttpPost postRequest = new HttpPost(url);

            // リクエストヘッダーを設定
            postRequest.setHeader("Authorization", "Bearer " + secretKey);
            postRequest.setHeader("Stripe-Version", "2024-04-10");
            postRequest.setHeader("Content-Type", "application/x-www-form-urlencoded");

            // リクエストボディを設定
            StringEntity params = new StringEntity("amount=2000&currency=usd&source=tok_visa");
            postRequest.setEntity(params);

            // リクエストを実行
            try (CloseableHttpResponse response = httpClient.execute(postRequest)) {
                // レスポンスを取得
                int statusCode = response.getStatusLine().getStatusCode();
                String responseBody = EntityUtils.toString(response.getEntity());

                // ステータスコードとレスポンスボディを出力
                System.out.println("Response Status Code: " + statusCode);
                System.out.println("Response Body: " + responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
