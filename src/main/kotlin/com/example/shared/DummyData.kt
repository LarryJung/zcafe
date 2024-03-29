package com.example.shared

//val menuList = listOf(
//    CafeMenu(
//        id = 1,
//        name = "아메리카노(HOT)",
//        price = 3800,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%A1%E1%84%86%E1%85%A6%E1%84%85%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%82%E1%85%A9(HOT).jpg"
//    ),
//    CafeMenu(
//        id = 2,
//        name = "아메리카노(ICE)",
//        price = 3800,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%A1%E1%84%86%E1%85%A6%E1%84%85%E1%85%B5%E1%84%8F%E1%85%A1%E1%84%82%E1%85%A9(ICE).jpg"
//    ),
//    CafeMenu(
//        id = 3,
//        name = "카페라떼(HOT)",
//        price = 4500,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8F%E1%85%A1%E1%84%91%E1%85%A6%E1%84%85%E1%85%A1%E1%84%84%E1%85%A6(HOT)%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 4,
//        name = "카페라떼(ICE)",
//        price = 4500,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8F%E1%85%A1%E1%84%91%E1%85%A6%E1%84%85%E1%85%A1%E1%84%84%E1%85%A6(ICE).jpg"
//    ),
//    CafeMenu(
//        id = 5,
//        name = "콜드브루",
//        price = 7000,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8F%E1%85%A9%E1%86%AF%E1%84%83%E1%85%B3%E1%84%87%E1%85%B3%E1%84%85%E1%85%AE%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 6,
//        name = "아인슈페너",
//        price = 5000,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%A1%E1%84%8B%E1%85%B5%E1%86%AB%E1%84%89%E1%85%B2%E1%84%91%E1%85%A6%E1%84%82%E1%85%A5%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 7,
//        name = "에스프레소",
//        price = 3500,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%A6%E1%84%89%E1%85%B3%E1%84%91%E1%85%B3%E1%84%85%E1%85%A6%E1%84%89%E1%85%A9%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 8,
//        name = "아포가토",
//        price = 6000,
//        category = CafeMenuCategory.COFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%A1%E1%84%91%E1%85%A9%E1%84%80%E1%85%A1%E1%84%90%E1%85%A9%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 9,
//        name = "딸기스무디",
//        price = 6000,
//        category = CafeMenuCategory.NONCOFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%84%E1%85%A1%E1%86%AF%E1%84%80%E1%85%B5%E1%84%89%E1%85%B3%E1%84%86%E1%85%AE%E1%84%83%E1%85%B5%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 10,
//        name = "말차라떼",
//        price = 5000,
//        category = CafeMenuCategory.NONCOFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%86%E1%85%A1%E1%86%AF%E1%84%8E%E1%85%A1%E1%84%85%E1%85%A1%E1%84%84%E1%85%A6.jpg"
//    ),
//    CafeMenu(
//        id = 11,
//        name = "홍차",
//        price = 3000,
//        category = CafeMenuCategory.NONCOFFEE,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%92%E1%85%A9%E1%86%BC%E1%84%8E%E1%85%A1.jpg"
//    ),
//    CafeMenu(
//        id = 12,
//        name = "얼그레이스콘",
//        price = 3300,
//        category = CafeMenuCategory.BAKERY,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%A5%E1%86%AF%E1%84%80%E1%85%B3%E1%84%85%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%89%E1%85%B3%E1%84%8F%E1%85%A9%E1%86%AB%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 13,
//        name = "우리밀 치아바타",
//        price = 4000,
//        category = CafeMenuCategory.BAKERY,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8B%E1%85%AE%E1%84%85%E1%85%B5%E1%84%86%E1%85%B5%E1%86%AF%20%E1%84%8E%E1%85%B5%E1%84%8B%E1%85%A1%E1%84%87%E1%85%A1%E1%84%90%E1%85%A1.jpg"
//    ),
//    CafeMenu(
//        id = 14,
//        name = "참깨베이글",
//        price = 3000,
//        category = CafeMenuCategory.BAKERY,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8E%E1%85%A1%E1%86%B7%E1%84%81%E1%85%A2%E1%84%87%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%80%E1%85%B3%E1%86%AF.jpg"
//    ),
//    CafeMenu(
//        id = 15,
//        name = "크로아상",
//        price = 3500,
//        category = CafeMenuCategory.BAKERY,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8F%E1%85%B3%E1%84%85%E1%85%A9%E1%84%8B%E1%85%A1%E1%84%89%E1%85%A1%E1%86%BC.jpg"
//    ),
//    CafeMenu(
//        id = 16,
//        name = "클럽샌드위치",
//        price = 7000,
//        category = CafeMenuCategory.BAKERY,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8F%E1%85%B3%E1%86%AF%E1%84%85%E1%85%A5%E1%86%B8%E1%84%89%E1%85%A2%E1%86%AB%E1%84%83%E1%85%B3%E1%84%8B%E1%85%B1%E1%84%8E%E1%85%B5%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 17,
//        name = "땅콩초코쿠키",
//        price = 2000,
//        category = CafeMenuCategory.DESSERT,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%84%E1%85%A1%E1%86%BC%E1%84%8F%E1%85%A9%E1%86%BC%E1%84%8E%E1%85%A9%E1%84%8F%E1%85%A9%E1%84%8F%E1%85%AE%E1%84%8F%E1%85%B5.jpg"
//    ),
//    CafeMenu(
//        id = 18,
//        name = "마카롱",
//        price = 2000,
//        category = CafeMenuCategory.DESSERT,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%86%E1%85%A1%E1%84%8F%E1%85%A1%E1%84%85%E1%85%A9%E1%86%BC.jpg"
//    ),
//    CafeMenu(
//        id = 19,
//        name = "꾸덕초코 브라우니", price = 2000,
//        category = CafeMenuCategory.DESSERT,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%81%E1%85%AE%E1%84%83%E1%85%A5%E1%86%A8%E1%84%8E%E1%85%A9%E1%84%8F%E1%85%A9%20%E1%84%87%E1%85%B3%E1%84%85%E1%85%A1%E1%84%8B%E1%85%AE%E1%84%82%E1%85%B5%20%E1%84%91%E1%85%A7%E1%86%AB%E1%84%8C%E1%85%B5%E1%86%B8.png"
//    ),
//    CafeMenu(
//        id = 20,
//        name = "치즈케이크",
//        price = 7000,
//        category = CafeMenuCategory.DESSERT,
//        image = "https://bucket-ogntr9.s3.ap-northeast-2.amazonaws.com/%EC%B9%B4%ED%8E%98%EB%A9%94%EB%89%B4/%E1%84%8E%E1%85%B5%E1%84%8C%E1%85%B3%E1%84%8F%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8F%E1%85%B3.jpg"
//    ),
//)

