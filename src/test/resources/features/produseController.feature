Feature: putem crea si cauta produse
  Scenario: clientul vrea sa caute un produs inexistent
    When clientul cauta un produs cu numele test123x
    Then clientul va primi un code de stare egal cu 400
    And clientul va primi un mesaj ce contine 'Nu a putut fi gasit produsul cu acest nume'
  Scenario: clientul creaza un produs
    When clientul creeaza un produs cu numele 'test321y' si costul '13.45'
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost creat'
  Scenario: clientul vrea sa caute un produse existent
    When clientul cauta un produs cu numele test321y
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un produs cu numele 'test321y' si costul '13.45'
  Scenario: clientul creaza un produs cu acelasi nume ca in scenariul anterior, dar un cost diferit
    When clientul creeaza un produs cu numele 'test321y' si costul '12.29'
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost creat'
  Scenario: clientul vrea sa caute un produs cu un nume ce se gaseste pe mai multe produse
    When clientul cauta un produs cu numele test321y
    Then clientul va primi un code de stare egal cu 400
    And clientul va primi un mesaj ce contine 'Numele se refera la mai multe produse'
  Scenario: clientul creaza un produs
    When clientul creeaza un produs cu numele 'test123x123' si costul '222'
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost creat'
  Scenario: clientul sterge un produs
    When clientul sterge un produs cu numele 'test123x123'
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost sters'
  Scenario: clientul vrea sa caute un produs sters
    When clientul cauta un produs cu numele test123x123
    Then clientul va primi un code de stare egal cu 400
    And clientul va primi un mesaj ce contine 'Nu a putut fi gasit produsul cu acest nume'
