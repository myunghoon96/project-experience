import collections
from itertools import combinations

def pairwise(arr):
    toks = list(dict.fromkeys(arr))
    pair = list(combinations(sorted(toks), 2))
    return pair

import pandas as pd
from konlpy.tag import Komoran

dataset = pd.read_csv("data/movieReview.txt", delimiter='\t', header=-1)
korstop = "불용어 .com !! 불용어 불용 이 볼 더 점 중간 좀 편 뭐 정말 것 별 왜 정도 시간 사람 내용 그 못 게 임 진짜 눈 용 역시 마지막 함 그냥 생각 보고 편이 연기 물 기대 거 내 나 난 이건 놀 나름 비 토 다시 지금 때 토로 토토 어물 만 듯 안".split(' ')

komoran = Komoran(userdic='data/user_dic.txt')
docs = []
for article in dataset[0]:
    words = komoran.nouns(article)
    tokens = []
    for word in words :
        if word not in korstop : tokens.append(word)
    docs.append(tokens)

pairs = []
for doc in docs :
    pair = pairwise(doc)
    pairs += pair

print(pairs)
count = collections.Counter(list(pairs))



import networkx as nx

graph=nx.Graph()
for key in count.keys() :
    print(key)
    graph.add_edge(key[0],key[1],weight=count.get(key))

nx.write_graphml(graph, "CoOccurence.graphml")

