from sklearn.feature_extraction.text import CountVectorizer
from sklearn.decomposition import LatentDirichletAllocation

import pandas as pd
from konlpy.tag import Komoran

dataset = pd.read_csv("data/movieReview.txt", delimiter='\t', header=-1)

komoran = Komoran()
korstop = "불용어 .com !! 불용어 불용 이 볼 더 점 중간 좀 편 뭐 정말 것 별 왜 정도 시간 사람 내용 그 못 게 임 진짜 눈 용 역시 마지막 함 그냥 생각 보고 편이 연기 물 기대 거 내 나 난 이건 놀 나름 비 토 다시 지금 때 토로 토토 어물 만 듯 안".split(' ')
cleaned = []
for article in dataset[0]:
    if komoran.nouns(article) not in korstop:
        words = komoran.nouns(article)
    cleaned.append(' '.join(words))

cv = CountVectorizer(stop_words="english", max_features=1000)
transformed = cv.fit_transform(cleaned)
lda = LatentDirichletAllocation(n_components=3, random_state=43).fit(transformed)

for topic_idx, topic in enumerate(lda.components_):
    label = '{}: '.format(topic_idx)
    print(label, " ".join([cv.get_feature_names()[i]
                           for i in topic.argsort()[:-9:-1]]))

doc_topic = lda.transform(transformed)
for n in range(doc_topic.shape[0]):
    topic_most_pr = doc_topic[n].argmax()
    print("doc: {} topic: {}".format(n,topic_most_pr))
