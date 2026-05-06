package com.definancy.sdk.demo;

import com.definancy.model.Asset;
import com.definancy.sdk.DefinancyApiException;
import com.definancy.sdk.DefinancyClient;

import java.util.List;

public class APIGetAssets {
    public static void main(String[] args) throws Exception {
        try (DefinancyClient definancy = Config.newClient()) {
            List<Asset> assets = definancy.assets().list();

            if (assets == null || assets.isEmpty()) {
                System.out.println("No assets found");
                return;
            }

            System.out.printf("Found %d assets:%n", assets.size());
            for (Asset asset : assets) {
                System.out.println(asset.getInfo().getName());
            }
        } catch (DefinancyApiException e) {
            Utils.printException(e, "assets", "list");
        } catch (Exception e) {
            Utils.printException(e, "assets", "list");
        }
    }
}
