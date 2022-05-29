Feature: putem sa utilizal cosul de cumparaturi
  Scenario: clientul vrea sa afle costul initial al cosului de cumparaturi
    When clientul cere costul total as cosului de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un cost total egal cu '0.0'
  Scenario: clientul vrea sa scoata din cosul de cumparaturi un produs inexistent
    When clientul scoate produsul test123x din cosul de cumparaturi
    Then clientul va primi un code de stare egal cu 400
    And clientul va primi un mesaj ce contine 'Produsul nu a putut fi scos'
  Scenario: clientul vrea sa verifice componenta unui cos de cumparaturi gol
    When clientul cere componenta cosului de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi o componenta goala
  Scenario: clientul vrea sa adauge un produs in cosul de cumparaturi
    When clientul adauga produsul test111z cu cantitatea 10 avand costul '13.375' in cosul de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost adaugat'
  Scenario: clientul vrea sa afle noul cost al cosului de cumparaturi
    When clientul cere costul total as cosului de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un cost total egal cu '133.75'
  Scenario: clientul vrea sa adauge un produs in cosul de cumparaturi
    When clientul adauga produsul test111zyx cu cantitatea 1 avand costul '14' in cosul de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost adaugat'
  Scenario: clientul vrea sa afle noul cost al cosului de cumparaturi
    When clientul cere costul total as cosului de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un cost total egal cu '147.75'
  Scenario: clientul vrea sa scoata din cosul de cumparaturi un produs
    When clientul scoate produsul test111z din cosul de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un mesaj ce contine 'Produsul a fost scos'
  Scenario: clientul vrea sa afle noul cost al cosului de cumparaturi
    When clientul cere costul total as cosului de cumparaturi
    Then clientul va primi un code de stare egal cu 200
    And clientul va primi un cost total egal cu '14.0'
  Scenario: clientul vrea sa adauge un produs inexistent in cosul de cumparaturi
    When clientul adauga produsul inexistent test321123x cu cantitatea 10 in cosul de cumparaturi
    Then clientul va primi un code de stare egal cu 400
    And clientul va primi un mesaj ce contine 'Produsul nu a putut fi adaugat'
