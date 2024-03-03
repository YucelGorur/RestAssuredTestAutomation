Feature: Kullanici olarak rezervasyonu olusturabilirim

  Scenario: Kullanici otel rezervasyonu olusturabilir ve rezervasyonu silebilir
    Given kullanici yeni bir rezervasyon olusturuyor
    And kullanici rezerasyon icin gereken bilgileri veriyor
    When kullanici otel rezervasyonu yaratiyor
    Then rezervasyon basarili sekilde olusuturuldu
    And Kullanici olusturulan rezervasyonu iptal ediyor