from collections import Counter

file = open('data/movieReview.txt','r',encoding='utf-8')
lines = file.readlines()

mReview = []
for line in lines:
    mReview.append(line)
file.close()

from konlpy.tag import Okt
twitter = Okt()

f = open("data/movieReviewProcess.txt", 'w',encoding='utf-8')
sentences_tag = []
for sentence in mReview:
    morph = twitter.pos(sentence)

    sentences_tag.append(morph)
    print(morph)
    print('-'*30)
    f.write(str(morph))
    f.write('\n')

print(sentences_tag)
print(len(sentences_tag))
print('\n'*3)
f.write('\n'*3)
f.write(str(sentences_tag))
f.write(str(len(sentences_tag)))
f.write('\n'*3)

noun_adj_list = []
korstop = "점 편 불용어 불용 이 볼 더 점 중간 좀 편 뭐 정말 것 별 왜 정도 시간 사람 내용 그 못 게 임 진짜 눈 용 역시 마지막 함 그냥 생각 보고 편이 연기 물 기대 거 내 나 난 이건 놀 나름 비 토 다시 지금 때 토로 토토 어물 만 듯 안".split(' ')
for sentence1 in sentences_tag:
    for word, tag in sentence1:
        if tag in ['Noun','Adjective','Verb']:
            if word not in korstop:
                noun_adj_list.append(word)

counts = Counter(noun_adj_list)
print(counts)
print(counts.most_common(10))
f.write(str(counts))
f.write('\n'*3)
f.write(str(counts.most_common(10)))
f.close()