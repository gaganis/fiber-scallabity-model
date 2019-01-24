package com.giorgosgaganis.fibers.scalability.model;

public class ViewAccountPage {
    final private ExternalService externalService;

    public ViewAccountPage(ExternalService externalService) {
        this.externalService = externalService;
    }

    public String render(String accountNumber) {
        String accountInformation = null;
        for (int i = 0; i < 5; i++) {
            accountInformation = externalService.getAccountInfo(accountNumber);
        }

        return "<html>" + accountInformation + "</html>";
    }
}
