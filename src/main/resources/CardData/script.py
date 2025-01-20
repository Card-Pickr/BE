from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.by import By
from selenium.common.exceptions import NoSuchWindowException
from selenium.common.exceptions import NoSuchElementException, NoSuchWindowException, WebDriverException
import csv
import time

# 브라우저 옵션 설정
chrome_options = Options()

# 크롬 드라이버 옵션 세팅
chrome_options.add_experimental_option("detach", True)  # 브라우저 창이 닫히지 않도록 설정
chrome_options.add_experimental_option('excludeSwitches', ['enable-logging'])  # 불필요한 로그 메시지 제거
chrome_options.add_argument("--start-maximized")  # 브라우저 창을 최대화 상태로 실행
chrome_options.add_argument("disable-infobars")  # '정보 표시줄' 비활성화

# 드라이버 실행
driver = webdriver.Chrome(options=chrome_options)

# CSV 파일 생성
f1 = open("C:/Users/user/WebProject/card_data.csv", 'w', encoding="CP949", newline='')  # 카드 관련 데이터 저장
card_data = csv.writer(f1)

f2 = open("C:/Users/user/WebProject/benefit_data.csv", 'w', encoding="CP949", newline='')  # 카드 혜택 데이터 저장
benefit_data = csv.writer(f2)

# 카드 ID 초기화
card_id = 0

# 카드 상세 정보 수집
for i in range(1, 10):  # 1부터 2435까지 반복
    try:
        # 카드 상세 페이지 접속
        driver.get(f"https://www.card-gorilla.com/card/detail/{i}")
        time.sleep(2)

        # 페이지 로딩 중 fixmenu 오류 방지
        driver.execute_script("document.querySelector('#q-app > header').style.visibility='hidden';")

        # 카드 데이터 수집
        card_id += 1

        card_name = driver.find_element(By.CSS_SELECTOR, "#q-app > section > div.card_detail.fr-view > section > div > article.card_top > div > div > div.data_area > div.tit > strong")
        card_name = card_name.text

        card_brand = driver.find_element(By.CSS_SELECTOR, "#q-app > section > div.card_detail.fr-view > section > div > article.card_top > div > div > div.data_area > div.tit > p")
        card_brand = card_brand.text

        card_image = driver.find_element(By.CSS_SELECTOR, "#q-app > section > div.card_detail.fr-view > section > div > article.card_top > div > div > div.plate_area > div > img")
        card_image = card_image.get_attribute('src')

        card_description = driver.find_element(By.CSS_SELECTOR, "#q-app > section > div.card_detail.fr-view > section > div > article.card_top > div > div > div.data_area > div.bnf1 ")
        card_description = card_description.text

        # 수집한 데이터 저장
        card_data.writerow([card_id, card_name, card_brand, card_description, card_image])

        # 카드 혜택 정보 수집cd
        benefit_sections = driver.find_elements(By.CSS_SELECTOR, "#q-app > section > div.card_detail.fr-view > section > div > article.cmd_con.benefit > div.lst.bene_area > dl")

        for section in benefit_sections:
            benefit_title = section.find_element(By.CSS_SELECTOR, "dt > p").text
            benefit_content = section.find_element(By.CSS_SELECTOR, "dt > i").text
            section.find_element(By.CSS_SELECTOR, "dt").click()
            benefit_content_all = section.find_element(By.CSS_SELECTOR, "dd > div.in_box").text
            # 혜택 정보를 파일에 기록
            benefit_data.writerow([card_id, benefit_title, benefit_content, benefit_content_all])

    except Exception as e:
        print(f"Error processing Card {i}: {e}", flush=True)
        continue

driver.quit()
f1.close()
f2.close()