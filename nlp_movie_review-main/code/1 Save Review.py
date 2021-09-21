from bs4 import BeautifulSoup
import urllib.request as req

import ssl
ssl._create_default_https_context = ssl._create_unverified_context
f = open("data/movieReview.txt", 'w',encoding='utf-8')

flag = True
page = 1
while flag :
    url = "https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=18781&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page="+str(page)
    #https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=69990&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page= 그린랜턴
    #https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=62560&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page= 헐크
    #https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=56612&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page= 호스텔1
    #https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=91505&type=&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page= 호스텔3
    #https://movie.naver.com/movie/bi/mi/pointWriteFormList.nhn?code=18781&type=after&isActualPointWriteExecute=false&isMileageSubscriptionAlready=false&isMileageSubscriptionReject=false&page= 이웃집 토토로
    res = req.urlopen(url)

    soup = BeautifulSoup(res, "html.parser",from_encoding='utf-8')
    totalScore = 0
    comments = soup.select("body > div > div > div.score_result > ul > li")
    for comment in comments:

        content = comment.select_one("div.score_reple > p").text.strip()

        print("댓글: ", content)

        f.write(content)
        f.write("\n")

    page = page + 1
    next = soup.find('a', {'class': "pg_next"})
    if next:
        continue
    else:
        flag = False
        f.close()
