#  📦 Evaluation — JPA / Hibernate

Trois exercices de gestion d'applications Java avec JPA et Hibernate.

---

## 🛠️ Technologies utilisées

- Java 8
- JPA 2.2
- Hibernate 5.6.5
- H2 Database (Exercice 1 & 2)
- MySQL 8 (Exercice 3)
- Apache Maven

---

## 📁 Structure du Repository

```
evaluation/
├── Exercice1/     → Gestion de Stock
├── Exercice2/     → Gestion de Projets
└── Exercice3/     → Gestion de l'État Civil
```

---

## 📗 Exercice 1 — Gestion de Stock

### Contexte
Application de gestion du stock d'un magasin de produits informatiques.

### Diagramme de classes

<img width="999" height="572" alt="Screenshot 2026-03-06 173929" src="https://github.com/user-attachments/assets/adb819c8-fa94-4a82-a4fd-22a7238f51f6" />


### Fonctionnalités
- CRUD complet pour chaque entité
- Afficher les produits par catégorie
- Afficher les produits commandés entre deux dates
- Afficher les produits d'une commande donnée (format tableau)
- Afficher les produits dont le prix > 100 DH via `@NamedQuery`

### Exemple de sortie
```
Commande : 1     Date : Thu Mar 14 00:00:00 GMT 2013
Liste des produits :
Référence    Prix       Quantité
ES12         120 DH     7
ZR85         100 DH     14
EE85         200 DH     5
```

### Captures d'écran

<img width="1664" height="785" alt="Screenshot 2026-03-06 150815" src="https://github.com/user-attachments/assets/317c035a-d3cf-47e2-8865-911be759e206" />

<img width="985" height="670" alt="Screenshot 2026-03-06 150837" src="https://github.com/user-attachments/assets/99ee14d2-128c-4b42-98f5-5998f8e50823" />

<img width="857" height="515" alt="Screenshot 2026-03-06 150853" src="https://github.com/user-attachments/assets/8e5d0797-3a3e-4f3e-87ff-b3b5596a328d" />

<img width="748" height="503" alt="Screenshot 2026-03-06 150912" src="https://github.com/user-attachments/assets/06733c98-9ba2-4646-92d1-46de3cac0e8e" />

<img width="809" height="398" alt="Screenshot 2026-03-06 150919" src="https://github.com/user-attachments/assets/a0c9a311-658d-4c81-90d0-118df68854ca" />

<img width="782" height="470" alt="Screenshot 2026-03-06 150936" src="https://github.com/user-attachments/assets/58cfacfe-0b6a-4121-ae85-2428c4f38a4d" />

<img width="789" height="502" alt="Screenshot 2026-03-06 150958" src="https://github.com/user-attachments/assets/8ad6752a-31cc-431a-a958-a41c82d8609e" />

<img width="697" height="464" alt="Screenshot 2026-03-06 151007" src="https://github.com/user-attachments/assets/9abf6aa5-d974-4cf8-b462-201c286e28c5" />

<img width="859" height="364" alt="Screenshot 2026-03-06 151021" src="https://github.com/user-attachments/assets/1d1bf811-7ae6-4ae4-9253-85ad9c389a6b" />

<img width="640" height="368" alt="Screenshot 2026-03-06 151029" src="https://github.com/user-attachments/assets/209760b7-b74b-4a44-8497-702fe2d5d1d1" />

<img width="705" height="497" alt="Screenshot 2026-03-06 151040" src="https://github.com/user-attachments/assets/04883258-9079-4d82-bc0d-130948fae53e" />

<img width="732" height="496" alt="Screenshot 2026-03-06 151058" src="https://github.com/user-attachments/assets/6b1f0f59-b5bf-4f56-88dd-736f4a57e665" />

<img width="798" height="454" alt="Screenshot 2026-03-06 151105" src="https://github.com/user-attachments/assets/320df6d6-7dd1-4368-a69d-110e3f163ced" />

<img width="908" height="630" alt="Screenshot 2026-03-06 151121" src="https://github.com/user-attachments/assets/f973303b-66e0-47d3-b3b0-96e2a5cffa27" />

<img width="885" height="627" alt="Screenshot 2026-03-06 151140" src="https://github.com/user-attachments/assets/fce5d487-ed25-4dfd-8ebf-bd57fa3114a3" />

<img width="986" height="666" alt="Screenshot 2026-03-06 151200" src="https://github.com/user-attachments/assets/0be5b345-b57e-41df-a9ed-40834bf9cd02" />

<img width="732" height="393" alt="Screenshot 2026-03-06 151229" src="https://github.com/user-attachments/assets/7e3cedb6-6af5-4d6b-ac40-51a7767ac60f" />

<img width="859" height="630" alt="Screenshot 2026-03-06 151245" src="https://github.com/user-attachments/assets/2f1c830b-6f21-4c59-baca-b6039031f63e" />

<img width="1119" height="662" alt="Screenshot 2026-03-06 151311" src="https://github.com/user-attachments/assets/804efc4a-e1c8-4eed-8384-ad0dec2b2206" />

---

## 📘 Exercice 2 — Gestion de Projets

### Contexte
Application de suivi du temps passé sur les projets et calcul des coûts pour un bureau d'études.

### Diagramme de classes

<img width="442" height="624" alt="Screenshot 2026-03-06 174726" src="https://github.com/user-attachments/assets/0b01f866-8097-49b8-9a3c-71a4b15ae884" />


### Fonctionnalités
- CRUD complet pour chaque entité
- Afficher les tâches réalisées par un employé
- Afficher les projets gérés par un employé
- Afficher les tâches planifiées pour un projet
- Afficher les tâches réalisées avec les dates réelles
- Afficher les tâches dont le prix > 1000 DH via `@NamedQuery`
- Afficher les tâches réalisées entre deux dates

### Exemple de sortie
```
Projet : 1      Nom : Gestion de stock     Date début : 14/01/2013
Liste des tâches:
Num  Nom             Date Début Réelle    Date Fin Réelle
1    Analyse         10/02/2013           20/02/2013
2    Conception      10/03/2013           15/03/2013
3    Développement   10/04/2013           25/04/2013
```

### Captures d'écran
<img width="1667" height="779" alt="Screenshot 2026-03-06 153252" src="https://github.com/user-attachments/assets/3bdd394b-3fa3-4796-94b5-2bd8bb60284a" />

<img width="893" height="672" alt="Screenshot 2026-03-06 153309" src="https://github.com/user-attachments/assets/836f8af0-6b96-41f3-9067-ec3dda1cb956" />

<img width="1260" height="462" alt="Screenshot 2026-03-06 153325" src="https://github.com/user-attachments/assets/d5f8f3d8-fb02-4c04-bc16-be59cfbb2a8e" />

<img width="1041" height="671" alt="Screenshot 2026-03-06 153343" src="https://github.com/user-attachments/assets/7fa3ca6f-01ac-4349-8aba-3fe93e57f7f0" />

<img width="860" height="601" alt="Screenshot 2026-03-06 153357" src="https://github.com/user-attachments/assets/1681047c-d03b-4a21-b157-a1c539e05598" />

<img width="1490" height="510" alt="Screenshot 2026-03-06 153408" src="https://github.com/user-attachments/assets/c712212f-534b-47ec-bf8e-65c824f99c36" />

<img width="1065" height="238" alt="Screenshot 2026-03-06 153455" src="https://github.com/user-attachments/assets/8fd969e7-a500-4f8c-9fd4-735d9c8501ba" />

<img width="1090" height="503" alt="Screenshot 2026-03-06 153506" src="https://github.com/user-attachments/assets/fcd9f091-ff24-4f63-9576-7b2c5a775820" />

<img width="898" height="498" alt="Screenshot 2026-03-06 153521" src="https://github.com/user-attachments/assets/073649c7-219e-48f8-9e1b-f1cdf04740e0" />

<img width="980" height="465" alt="Screenshot 2026-03-06 153529" src="https://github.com/user-attachments/assets/5237e243-0c84-4e66-b683-d344834e2b3c" />

<img width="953" height="501" alt="Screenshot 2026-03-06 153542" src="https://github.com/user-attachments/assets/1897c111-e77d-4c36-aec0-a72c4399a506" />

<img width="1037" height="466" alt="Screenshot 2026-03-06 153550" src="https://github.com/user-attachments/assets/a76d9bf0-9edc-46d1-9219-85e4b685be33" />

<img width="913" height="531" alt="Screenshot 2026-03-06 153600" src="https://github.com/user-attachments/assets/193389a6-e7cf-4f63-8a62-5f47c1f7fa27" />

<img width="967" height="661" alt="Screenshot 2026-03-06 153614" src="https://github.com/user-attachments/assets/414a9908-238b-4e29-837b-df99cdecdd40" />

<img width="942" height="409" alt="Screenshot 2026-03-06 153634" src="https://github.com/user-attachments/assets/c1c40637-c40f-42c1-83e6-e626cac6e67b" />

<img width="941" height="369" alt="Screenshot 2026-03-06 153640" src="https://github.com/user-attachments/assets/fccd0f59-99e4-4c2e-a54b-32dc67a5b4ce" />

<img width="959" height="442" alt="Screenshot 2026-03-06 153650" src="https://github.com/user-attachments/assets/6e07ea16-71c9-4699-96a3-276a169459ca" />

<img width="922" height="660" alt="Screenshot 2026-03-06 153727" src="https://github.com/user-attachments/assets/a8e014fa-45dd-4a2b-b6d4-9c8d6c2c6f31" />

<img width="965" height="463" alt="Screenshot 2026-03-06 153743" src="https://github.com/user-attachments/assets/87724723-20c9-4fbc-bb35-05a8495d9448" />

<img width="1008" height="636" alt="Screenshot 2026-03-06 153807" src="https://github.com/user-attachments/assets/74963f78-9f22-46e5-a178-44cea8abe629" />

<img width="970" height="658" alt="Screenshot 2026-03-06 153831" src="https://github.com/user-attachments/assets/6f93b39a-232f-4a60-bea8-b9c209663844" />

<img width="897" height="528" alt="Screenshot 2026-03-06 153845" src="https://github.com/user-attachments/assets/38ee9f5f-379f-4296-adc1-b504b262e8a1" />

<img width="870" height="663" alt="Screenshot 2026-03-06 153901" src="https://github.com/user-attachments/assets/d7a062fb-4037-4bc6-b815-f4e378a4533d" />

<img width="1681" height="647" alt="Screenshot 2026-03-06 153922" src="https://github.com/user-attachments/assets/1bb5a6e2-3a48-4959-bfda-589cceff2452" />


---

## 📕 Exercice 3 — Gestion de l'État Civil

### Contexte
Application de gestion des citoyens et de leurs relations matrimoniales dans une province.

### Diagramme de classes

<img width="542" height="444" alt="Screenshot 2026-03-06 175435" src="https://github.com/user-attachments/assets/b5e3a60c-7214-4476-b8ca-edfb478c143b" />

### Points clés
- Héritage JPA avec la stratégie `JOINED`
- `@NamedNativeQuery` — nombre d'enfants d'une femme entre deux dates
- `@NamedQuery` — femmes mariées au moins deux fois
- `API Criteria` — hommes mariés à 4 femmes entre deux dates

### Fonctionnalités
- CRUD complet pour chaque entité
- Afficher la liste des femmes
- Afficher la femme la plus âgée
- Afficher les épouses d'un homme entre deux dates
- Afficher le nombre d'enfants d'une femme entre deux dates
- Afficher les femmes mariées deux fois ou plus
- Afficher les hommes mariés à 4 femmes entre deux dates (Criteria API)
- Afficher les mariages d'un homme avec tous les détails

### Exemple de sortie
```
Nom : SAFI SAID

Mariages En Cours :
1. Femme : RAMI SALIMA   Date Début : 03/09/1990    Nbr Enfants : 4
2. Femme : ALI AMAL      Date Début : 03/09/1995    Nbr Enfants : 2
3. Femme : ALAOUI WAFA   Date Début : 04/11/2000    Nbr Enfants : 3

Mariages échoués :
1. Femme : ALAMI KARIMA  Date Début : 03/09/1989
   Date Fin : 03/09/1990    Nbr Enfants : 0
```

### Captures d'écran
<img width="1721" height="784" alt="Screenshot 2026-03-06 161458" src="https://github.com/user-attachments/assets/5af7af99-8fd5-425a-84bd-b860b5815d71" />

<img width="1339" height="568" alt="Screenshot 2026-03-06 161513" src="https://github.com/user-attachments/assets/af89e3d2-e933-45ba-af64-6b928881cce4" />

<img width="737" height="574" alt="Screenshot 2026-03-06 161526" src="https://github.com/user-attachments/assets/ba4d8cc5-4c7b-4d32-b0b2-0011cefeda28" />

<img width="1185" height="514" alt="Screenshot 2026-03-06 161541" src="https://github.com/user-attachments/assets/f0931c1d-b079-44e1-843e-6d7fa631478f" />

<img width="1031" height="649" alt="Screenshot 2026-03-06 161701" src="https://github.com/user-attachments/assets/57b8cf8f-02e9-4fb6-b859-a96aa07bc8e2" />

<img width="886" height="589" alt="Screenshot 2026-03-06 161718" src="https://github.com/user-attachments/assets/758190fa-ea3f-455b-9469-fd0243efe7b8" />

<img width="1491" height="494" alt="Screenshot 2026-03-06 161732" src="https://github.com/user-attachments/assets/79c6d395-f0b5-4ba0-ba8d-ebb699667759" />

<img width="922" height="466" alt="Screenshot 2026-03-06 161749" src="https://github.com/user-attachments/assets/edf0054e-923b-4cb8-96be-af4365a8447a" />

<img width="1095" height="664" alt="Screenshot 2026-03-06 161832" src="https://github.com/user-attachments/assets/49d9bbb8-775a-4d23-b89d-608ac1df0095" />

<img width="1114" height="667" alt="Screenshot 2026-03-06 161840" src="https://github.com/user-attachments/assets/87c89b50-e255-4f49-8d81-2bb6455d3465" />

<img width="1092" height="669" alt="Screenshot 2026-03-06 161849" src="https://github.com/user-attachments/assets/67245480-1d7c-4821-8d5b-37a06278d7ac" />

<img width="1084" height="669" alt="Screenshot 2026-03-06 161858" src="https://github.com/user-attachments/assets/770e09fa-bc38-4583-b38d-050133b0dc1b" />

<img width="1099" height="665" alt="Screenshot 2026-03-06 161906" src="https://github.com/user-attachments/assets/e75c5019-3cc4-49e3-ae44-bed1dfb34680" />

<img width="978" height="470" alt="Screenshot 2026-03-06 161916" src="https://github.com/user-attachments/assets/3b8d6c14-e5a0-444a-bf35-98d8694bdc3d" />

<img width="831" height="490" alt="Screenshot 2026-03-06 161939" src="https://github.com/user-attachments/assets/3b2dffa5-1899-4736-9a8b-abdf5f3a3769" />

<img width="890" height="468" alt="Screenshot 2026-03-06 161949" src="https://github.com/user-attachments/assets/e2611bda-c507-43a5-b549-4f712b4a837d" />

<img width="1082" height="669" alt="Screenshot 2026-03-06 162000" src="https://github.com/user-attachments/assets/2a0bfdd3-2edf-431f-bde1-a5adb4bf9439" />

<img width="1021" height="667" alt="Screenshot 2026-03-06 162021" src="https://github.com/user-attachments/assets/c74773b8-ae6d-4f54-ad1b-1e3bf7244ae8" />

<img width="1012" height="668" alt="Screenshot 2026-03-06 162032" src="https://github.com/user-attachments/assets/6b109de3-1832-4caa-b719-c5d21d20f27f" />

<img width="1127" height="670" alt="Screenshot 2026-03-06 162118" src="https://github.com/user-attachments/assets/6d3c52b8-ca72-44b1-a2ec-4223cfd54c40" />

<img width="1107" height="674" alt="Screenshot 2026-03-06 162127" src="https://github.com/user-attachments/assets/f5e3bfca-577a-4471-b0a1-8ff10f2fb2e3" />

<img width="846" height="470" alt="Screenshot 2026-03-06 162136" src="https://github.com/user-attachments/assets/233154c7-e6e8-4375-832f-4c7cb2dc926a" />

<img width="823" height="528" alt="Screenshot 2026-03-06 162154" src="https://github.com/user-attachments/assets/1d28b197-05ec-4577-97a3-d6f09658c9c3" />

<img width="915" height="353" alt="Screenshot 2026-03-06 162201" src="https://github.com/user-attachments/assets/d2872b53-181d-40c7-bf24-fa173c24d58b" />

<img width="908" height="569" alt="Screenshot 2026-03-06 162210" src="https://github.com/user-attachments/assets/09e5e5a5-648b-47e2-8090-7fd17d0ed1b3" />

<img width="899" height="647" alt="Screenshot 2026-03-06 162229" src="https://github.com/user-attachments/assets/a83b5590-58f4-4524-8013-0e60e11281a8" />

<img width="916" height="321" alt="Screenshot 2026-03-06 162236" src="https://github.com/user-attachments/assets/1aca4d86-443e-4e7f-bc9a-1d0c77753c99" />

<img width="875" height="325" alt="Screenshot 2026-03-06 162243" src="https://github.com/user-attachments/assets/fc1d8eb1-9b79-4cac-acc5-9c78d4b423bb" />

<img width="835" height="651" alt="Screenshot 2026-03-06 162332" src="https://github.com/user-attachments/assets/2bf7591a-92ba-42c4-a3d2-f16ba044d655" />

<img width="754" height="352" alt="Screenshot 2026-03-06 162339" src="https://github.com/user-attachments/assets/db8f0ee6-ef00-4100-b081-2b30ed372f22" />

<img width="861" height="567" alt="Screenshot 2026-03-06 162353" src="https://github.com/user-attachments/assets/ca51e051-0000-4d7c-b480-579dc237fd13" />

<img width="735" height="244" alt="Screenshot 2026-03-06 162359" src="https://github.com/user-attachments/assets/3d9b47eb-f0bb-4167-aef5-04376fd95d5b" />

<img width="767" height="503" alt="Screenshot 2026-03-06 162411" src="https://github.com/user-attachments/assets/1ac3eb1d-f947-44f0-87eb-755855202ee6" />

<img width="876" height="517" alt="Screenshot 2026-03-06 162420" src="https://github.com/user-attachments/assets/3bc720d3-8dce-410c-b083-d95a7b9887b0" />

<img width="885" height="482" alt="Screenshot 2026-03-06 162428" src="https://github.com/user-attachments/assets/b3bcab58-bee1-418f-80c8-6423800debb8" />

<img width="941" height="481" alt="Screenshot 2026-03-06 162438" src="https://github.com/user-attachments/assets/665e2603-50d1-4572-8e9f-c7a072139c88" />

<img width="880" height="505" alt="Screenshot 2026-03-06 162449" src="https://github.com/user-attachments/assets/17e09721-339c-4ce5-bea1-ad75d21a09c2" />

<img width="820" height="547" alt="Screenshot 2026-03-06 162501" src="https://github.com/user-attachments/assets/5d4779ba-e898-410c-8cf9-10aa3c3244ac" />

<img width="1112" height="600" alt="Screenshot 2026-03-06 162516" src="https://github.com/user-attachments/assets/4883a883-4e70-4864-8a0e-f92480de405f" />

<img width="1678" height="564" alt="Screenshot 2026-03-06 162544" src="https://github.com/user-attachments/assets/08c87276-523d-499e-b0a7-b5dcd09833c8" />

<img width="1688" height="445" alt="Screenshot 2026-03-06 162555" src="https://github.com/user-attachments/assets/61842494-5329-4356-a7ae-4eb5d52a1989" />



