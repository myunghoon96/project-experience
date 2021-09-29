import pytagcloud
import random
import webbrowser
from konlpy.tag import Okt
from collections import Counter


def get_tags(text, ntags=30, multiplier=1):#개수, 가중치
    t = Okt()
    nouns = []

    korstop = "불용어 불용 말 데 분 아분 이번 또 잼 이제 편 감 다음 수 놈 걸 재 이영화 이 볼 더 점 중간 좀 편 뭐 정말 것 별 왜 정도 시간 사람 내용 그 못 게 임 진짜 눈 용 역시 마지막 함 그냥 가슴 생각 보고 편이 연기 물 기대 거 내 나 난 이건 놀 나름 비 토 다시 지금 때 토로 토토 어물 만 듯 안".split(' ')

    for sentence in text:
        for noun in t.nouns(sentence):
            if noun not in korstop:
                nouns.append(noun)

            count = Counter(nouns)

    return [{'color': color(),'tag':n,'size':1*c*multiplier} for n,c in count.most_common(ntags)]

def draw_cloud(tags, filename, fontname = 'Noto Sans CJK',size1 = (2500,2000)):#1300 800
    pytagcloud.create_tag_image(tags,filename,fontname=fontname,size=size1)
    webbrowser.open(filename)

r = lambda: random.randint(0, 255)
color = lambda: (r(), r(), r())

mReview = []
file = open('data/movieReview.txt', 'r', encoding ='utf-8')
lines = file.readlines()

for line in lines:
    mReview.append(line)
file.close()

tags = get_tags(mReview)
print(tags)
draw_cloud(tags,'wordCloud30.png')

